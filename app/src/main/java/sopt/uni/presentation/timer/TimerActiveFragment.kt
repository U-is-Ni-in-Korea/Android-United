package sopt.uni.presentation.timer

import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import sopt.uni.R
import sopt.uni.databinding.FragmentTimerActiveBinding
import sopt.uni.util.binding.BindingFragment

@AndroidEntryPoint
class TimerActiveFragment :
    BindingFragment<FragmentTimerActiveBinding>(R.layout.fragment_timer_active) {
    private val viewModel by activityViewModels<TimerViewModel>()
    private val updateIntervalMillis = 1L

    private val updateTimer = object : CountDownTimer(Long.MAX_VALUE, updateIntervalMillis) {
        override fun onTick(millisUntilFinished: Long) {
            getLeftTime()
        }

        override fun onFinish() {

        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel
        initCircularProgressBar()
        updateTimer.start()
    }




    private fun getLeftTime() {
        // SharedPreferences에서 타이머 계산 결과를 읽어옴
        val sharedPreferences =
            requireContext().getSharedPreferences("timer_prefs", Context.MODE_PRIVATE)
        val totalSeconds = sharedPreferences.getLong("remainingSeconds", 0)
        viewModel.updateLeftTime(totalSeconds) // totalSeconds 값을 설정

    }

    private fun initCircularProgressBar() {
        val sharedPreferences =
            requireContext().getSharedPreferences("timer_prefs", Context.MODE_PRIVATE)
        val totalTime = sharedPreferences.getFloat("total_time", 0F)

        viewModel.setMaxTime(totalTime)

        viewModel.leftTime.observe(viewLifecycleOwner) { time ->
            binding.circularProgressBar.progress = time.toFloat()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Fragment가 파괴될 때 타이머를 중지해야 합니다.
        updateTimer.cancel()
    }
}
