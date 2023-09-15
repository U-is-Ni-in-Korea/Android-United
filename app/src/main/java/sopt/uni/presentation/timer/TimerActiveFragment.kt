package sopt.uni.presentation.timer

import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.core.content.ContextCompat
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
        startTimer()
        deleteTimer()
        timerEnd()
        pauseResumeTimer()
    }

    private fun buttonState() {
        val sharedPreferences =
            requireContext().getSharedPreferences(NAME, Context.MODE_PRIVATE)
        val isPause = sharedPreferences.getBoolean(PAUSEKEY, false)

        if (isPause) {
            binding.btnTimerStop.visibility = View.GONE
            binding.btnTimerContinue.visibility = View.VISIBLE
        }
    }

    private fun pauseResumeTimer() {
        val sharedPreferences =
            requireContext().getSharedPreferences(NAME, Context.MODE_PRIVATE)
        binding.btnTimerStop.setOnSingleClickListener {
            stopTimer()
            sharedPreferences.edit().putBoolean(PAUSEKEY, true).apply()
            binding.btnTimerStop.visibility = View.GONE
            binding.btnTimerContinue.visibility = View.VISIBLE
            binding.circularProgressBar.progressBarColor =
                ContextCompat.getColor(requireContext(), R.color.Lightblue_150)
        }
        binding.btnTimerContinue.setOnSingleClickListener {
            sharedPreferences.edit().putBoolean(PAUSEKEY, false).apply()
            startTimer()
            binding.btnTimerContinue.visibility = View.GONE
            binding.btnTimerStop.visibility = View.VISIBLE
            binding.circularProgressBar.progressBarColor =
                ContextCompat.getColor(requireContext(), R.color.Lightblue_500)
        }
    }

    private fun deleteTimer() {
        binding.btnTimerLeft.setOnSingleClickListener {
            val sharedPreferences =
                requireContext().getSharedPreferences(NAME, Context.MODE_PRIVATE)
            sharedPreferences.edit().putBoolean(ACTIVEKEY, false).apply()
            sharedPreferences.edit().remove(REMAINTIMEKEY).apply()
            sharedPreferences.edit().remove(TOTALTIMEKEY).apply()

            stopTimer()
            goTimerSettingFragment()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopTimer()
    }

    private fun getLeftTime() {
        // SharedPreferences에서 타이머 계산 결과를 읽어옴
        val sharedPreferences =
            requireContext().getSharedPreferences(NAME, Context.MODE_PRIVATE)
        val totalSeconds = sharedPreferences.getLong(REMAINTIMEKEY, totalTime.toLong())
        viewModel.updateLeftTime(totalSeconds) // totalSeconds 값을 설정
    }

    private fun initCircularProgressBar() {
        val sharedPreferences =
            requireContext().getSharedPreferences(NAME, Context.MODE_PRIVATE)
        val totalTime = sharedPreferences.getFloat(TOTALTIMEKEY, 0F)
        val isPause = sharedPreferences.getBoolean(PAUSEKEY, false)

        viewModel.setMaxTime(totalTime)
        viewModel.leftTime.observe(viewLifecycleOwner) { time ->
            time?.let {
                binding.circularProgressBar.progress = it.toFloat()
            }
        }

        if (!isPause) {
            binding.circularProgressBar.progressBarColor =
                ContextCompat.getColor(requireContext(), R.color.Lightblue_500)
        } else {
            binding.circularProgressBar.progressBarColor =
                ContextCompat.getColor(requireContext(), R.color.Lightblue_150)
        }

    }

    private fun stopTimer() {
        updateTimer.cancel()
    }

    private fun startTimer() {
        updateTimer.start()
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
        private const val NAME = "timer_prefs"
        private const val PAUSEKEY = "isTimerPause"
        private const val ACTIVEKEY = "isTimerActive"
        private const val TOTALTIMEKEY = "totalTime"
        private const val REMAINTIMEKEY = "remainingSeconds"
    }
}
