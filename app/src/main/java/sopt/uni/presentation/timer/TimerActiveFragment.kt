package sopt.uni.presentation.timer

import android.content.Context
import android.content.SharedPreferences
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
    private val updateIntervalMillis = INTERVAL
    private var totalTime = total
    private val sharedPreferences: SharedPreferences by lazy {
        requireContext().getSharedPreferences(NAME, Context.MODE_PRIVATE)
    }

    private val updateTimer =
        object : CountDownTimer(
            (totalTime * MILLISECONDS + updateIntervalMillis).roundToLong(),
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
        stateTimer()
        initCircularProgressBar()
        startTimer()
        deleteTimer()
        timerEnd()
        pauseTimer()
        resumeTimer()
    }

    private fun stateTimer() {
        val isPause = sharedPreferences.getBoolean(PAUSEKEY, false)

        if (isPause) {
            binding.btnTimerStop.visibility = View.GONE
            binding.btnTimerContinue.visibility = View.VISIBLE
            binding.circularProgressBar.progressBarColor =
                ContextCompat.getColor(requireContext(), R.color.Lightblue_150)
        } else {
            binding.circularProgressBar.progressBarColor =
                ContextCompat.getColor(requireContext(), R.color.Lightblue_500)
        }
    }

    private fun pauseTimer() {
        stopTimer()
        sharedPreferences.edit().putBoolean(PAUSEKEY, true).apply()
        binding.btnTimerStop.visibility = View.GONE
        binding.btnTimerContinue.visibility = View.VISIBLE
        binding.circularProgressBar.progressBarColor =
            ContextCompat.getColor(requireContext(), R.color.Lightblue_150)
    }

    private fun resumeTimer() {
        sharedPreferences.edit().putBoolean(PAUSEKEY, false).apply()
        startTimer()
        binding.btnTimerContinue.visibility = View.GONE
        binding.btnTimerStop.visibility = View.VISIBLE
        binding.circularProgressBar.progressBarColor =
            ContextCompat.getColor(requireContext(), R.color.Lightblue_500)
    }


    private fun deleteTimer() {
        binding.btnTimerLeft.setOnSingleClickListener {
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
        val totalSeconds = sharedPreferences.getLong(REMAINTIMEKEY, totalTime.toLong())
        viewModel.updateLeftTime(totalSeconds)
    }

    private fun initCircularProgressBar() {
        val totalTime = sharedPreferences.getFloat(TOTALTIMEKEY, ZEROFLOAT)

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

    private fun startTimer() {
        updateTimer.start()
    }

    private fun timerEnd() {
        viewModel.leftTime.observe(viewLifecycleOwner) { time ->
            if (time == ZEROLONG) {
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
        private const val INTERVAL = 100L
        private const val MILLISECONDS = 1000
        private const val ZEROFLOAT = 0F
        private const val ZEROLONG = 0L

    }
}
