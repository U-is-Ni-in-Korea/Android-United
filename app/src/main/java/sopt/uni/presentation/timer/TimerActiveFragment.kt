package sopt.uni.presentation.timer

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import sopt.uni.R
import sopt.uni.data.datasource.local.SparkleStorage
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
        val isPause = SparkleStorage.isPause

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
        binding.btnTimerStop.setOnSingleClickListener {
            stopTimer()
            SparkleStorage.isPause = true
            binding.btnTimerStop.visibility = View.GONE
            binding.btnTimerContinue.visibility = View.VISIBLE
            binding.circularProgressBar.progressBarColor =
                ContextCompat.getColor(requireContext(), R.color.Lightblue_150)
        }
    }

    private fun resumeTimer() {
        binding.btnTimerContinue.setOnSingleClickListener {
            SparkleStorage.isPause = false
            startTimer()
            binding.btnTimerContinue.visibility = View.GONE
            binding.btnTimerStop.visibility = View.VISIBLE
            binding.circularProgressBar.progressBarColor =
                ContextCompat.getColor(requireContext(), R.color.Lightblue_500)
        }
    }

    private fun deleteTimer() {
        binding.btnTimerLeft.setOnSingleClickListener {
            SparkleStorage.timerClear()
            stopTimer()
            goTimerSettingFragment()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopTimer()
    }

    private fun getLeftTime() {
        val totalSeconds = SparkleStorage.remainTime
        viewModel.updateLeftTime(totalSeconds)
    }

    private fun initCircularProgressBar() {
        val totalTime = SparkleStorage.totalTime

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
        private const val INTERVAL = 100L
        private const val MILLISECONDS = 1000
        private const val ZEROLONG = 0L
    }
}
