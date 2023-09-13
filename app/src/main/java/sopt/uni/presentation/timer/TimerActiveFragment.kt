package sopt.uni.presentation.timer

import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import sopt.uni.R
import sopt.uni.databinding.FragmentTimerActiveBinding
import sopt.uni.util.binding.BindingFragment
import sopt.uni.util.extension.setOnSingleClickListener
import kotlin.math.roundToLong

@AndroidEntryPoint
class TimerActiveFragment(total: Float) :
    BindingFragment<FragmentTimerActiveBinding>(R.layout.fragment_timer_active) {
    private val viewModel by activityViewModels<TimerViewModel>()
    private val updateIntervalMillis = 100L
    private var totalTime = total

    private val updateTimer =
        object : CountDownTimer(
            (totalTime * 1000 + updateIntervalMillis).roundToLong(),
            updateIntervalMillis
        ) {
            override fun onTick(millisUntilFinished: Long) {
                getLeftTime()
            }

            override fun onFinish() {}
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel
        buttonState()
        initCircularProgressBar()
        updateTimer.start()
        deleteTimer()
        timerEnd()
        pauseTimer()

    }

    private fun buttonState() {
        val sharedPreferences =
            requireContext().getSharedPreferences("timer_prefs", Context.MODE_PRIVATE)
        val isPause = sharedPreferences.getBoolean("isTimerPause", false)

        if (isPause) {
            binding.btnTimerStop.visibility = View.GONE
            binding.btnTimerContinue.visibility = View.VISIBLE
        }
    }


    private fun pauseTimer() {
        val sharedPreferences =
            requireContext().getSharedPreferences("timer_prefs", Context.MODE_PRIVATE)
        binding.btnTimerStop.setOnSingleClickListener {
            sharedPreferences.edit().putBoolean("isTimerPause", true).apply()
            binding.btnTimerStop.visibility = View.GONE
            binding.btnTimerContinue.visibility = View.VISIBLE
        }

        binding.btnTimerContinue.setOnSingleClickListener {
            sharedPreferences.edit().putBoolean("isTimerPause", false).apply()
            updateTimer.start()
            binding.btnTimerContinue.visibility = View.GONE
            binding.btnTimerStop.visibility = View.VISIBLE
        }

    }

    private fun deleteTimer() {
        binding.btnTimerLeft.setOnSingleClickListener {
            val sharedPreferences =
                requireContext().getSharedPreferences("timer_prefs", Context.MODE_PRIVATE)
            sharedPreferences.edit().putBoolean("isTimerActive", false).apply()
            sharedPreferences.edit().remove("remainingSeconds").apply()
            sharedPreferences.edit().remove("total_time").apply()

            stopTimer()
            goTimerSettingFragment()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Fragment가 파괴될 때 타이머를 중지해야 합니다.
        stopTimer()
    }


    private fun getLeftTime() {
        // SharedPreferences에서 타이머 계산 결과를 읽어옴
        val sharedPreferences =
            requireContext().getSharedPreferences("timer_prefs", Context.MODE_PRIVATE)
        val totalSeconds = sharedPreferences.getLong("remainingSeconds", totalTime.toLong())
        viewModel.updateLeftTime(totalSeconds) // totalSeconds 값을 설정
    }

    private fun initCircularProgressBar() {
        val sharedPreferences =
            requireContext().getSharedPreferences("timer_prefs", Context.MODE_PRIVATE)
        val totalTime = sharedPreferences.getFloat("total_time", 0F)

        viewModel.setMaxTime(totalTime)

        viewModel.leftTime.observe(viewLifecycleOwner) { time ->
            time?.let {
                binding.circularProgressBar.progress = it.toFloat()
            }
        }
    }

    private fun stopTimer() {
        updateTimer.cancel()
    }

    private fun timerEnd() {
        viewModel.leftTime.observe(viewLifecycleOwner) { time ->
            if (time == 0L) {
                viewModel.updateLeftTime(null)
                viewModel.setSnackbarMessage(SNACKBARMESSAGE)
                goTimerSettingFragment()
            }

        }

    }

    private fun goTimerSettingFragment() {
        val fragmentTimerSetting = TimerSettingFragment()
        val fragmentTransaction = parentFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fcv_timer, fragmentTimerSetting)
        fragmentTransaction.commit()
    }

    companion object {
        private const val SNACKBARMESSAGE = "타이머가 종료되었어요."

    }

}
