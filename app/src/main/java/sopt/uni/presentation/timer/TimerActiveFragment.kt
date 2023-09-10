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

@AndroidEntryPoint
class TimerActiveFragment(total: Long) :
    BindingFragment<FragmentTimerActiveBinding>(R.layout.fragment_timer_active) {
    private val viewModel by activityViewModels<TimerViewModel>()
    private val updateIntervalMillis = 100L
    private var totalTime = total

    private val updateTimer =
        object : CountDownTimer(totalTime * 1000 + updateIntervalMillis, updateIntervalMillis) {
            override fun onTick(millisUntilFinished: Long) {
                getLeftTime()
            }

            override fun onFinish() {
                timerEnd()
                /** TODO 타이머 종료시 진동, 알람 기능 **/
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel
        initCircularProgressBar()
        deleteTimer()
        updateTimer.start()

    }

    private fun deleteTimer() {
        binding.btnTimerLeft.setOnSingleClickListener {
            val sharedPreferences =
                requireContext().getSharedPreferences("timer_prefs", Context.MODE_PRIVATE)
            sharedPreferences.edit().putBoolean("isTimerActive", false).apply()
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
        val totalSeconds = sharedPreferences.getLong("remainingSeconds", totalTime)
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

    private fun stopTimer() {
        updateTimer.cancel()
    }

    private fun timerEnd() {
        viewModel.setSnackbarMessage(SNACKBARMESSAGE)
        goTimerSettingFragment()
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
