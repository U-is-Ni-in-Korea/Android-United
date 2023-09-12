package sopt.uni.presentation.timer

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import sopt.uni.R
import sopt.uni.databinding.FragmentTimerActiveBinding
import sopt.uni.util.binding.BindingFragment
import sopt.uni.util.extension.setOnSingleClickListener
import java.util.Timer
import javax.inject.Inject

@AndroidEntryPoint
class TimerActiveFragment @Inject constructor(total: Long) :
    BindingFragment<FragmentTimerActiveBinding>(R.layout.fragment_timer_active) {
    lateinit var timerStartActivity: TimerStartActivity
    private val viewModel by activityViewModels<TimerViewModel>()
    private var totalTime = total

    @Inject
    lateinit var timerTask: Timer

    override fun onAttach(context: Context) {
        super.onAttach(context)
        timerStartActivity = context as TimerStartActivity

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel

//        initCircularProgressBar()
//        updateTimer.start()
//        deleteTimer()
//        timerEnd()
        startTimer()
        pauseTimer()
        timerEnd()
    }

    private fun startTimer() {
//        val sharedPreferences =
//            requireContext().getSharedPreferences("timer_prefs", Context.MODE_PRIVATE)
//        val reActive = sharedPreferences.getBoolean("isTimerReActive", false)
//
//        if (reActive){
//            runTimer()
//            sharedPreferences.edit().putBoolean("isTimerReActive", false).apply()
//        }

        initCircularProgressBar()
        runTimer()

//        viewModel.isPaused.observe(viewLifecycleOwner) { pause ->
//            if (!pause) {
//                runTimer()
//            }
//
//        }
    }


    private fun runTimer() {
        val sharedPreferences =
            requireContext().getSharedPreferences("timer_prefs", Context.MODE_PRIVATE)

        timerTask.cancel()

        if (totalTime >= 0) {
            timerTask = kotlin.concurrent.timer(period = 1000) { // timer() 호출
                if (totalTime < 0) {
                    sharedPreferences.edit().putBoolean("isTimerActive", false).apply()
                    timerTask.cancel()
                } else {
                    totalTime--
                    Log.d("Timer", "Remaining Seconds: $totalTime")

                    sharedPreferences.edit().putLong("remainingSeconds", totalTime).apply()
                    // UI조작을 위한 메서드
                    timerStartActivity.runOnUiThread {
                        getLeftTime()
                    }
                }
            }
        }

    }


    private fun pauseTimer() {
        val sharedPreferences =
            requireContext().getSharedPreferences("timer_prefs", Context.MODE_PRIVATE)
        binding.btnTimerStop.setOnSingleClickListener {
            viewModel.setPauseState(true)
            timerTask.cancel()
            sharedPreferences.edit().putBoolean("isTimerPause", true).apply()
            binding.btnTimerStop.visibility = View.GONE
            binding.btnTimerContinue.visibility = View.VISIBLE
        }

        binding.btnTimerContinue.setOnSingleClickListener {
            runTimer()
            viewModel.setPauseState(false)
            sharedPreferences.edit().putBoolean("isTimerPause", false).apply()
            binding.btnTimerContinue.visibility = View.GONE
            binding.btnTimerStop.visibility = View.VISIBLE
        }

    }

//    private fun deleteTimer() {
//        binding.btnTimerLeft.setOnSingleClickListener {
//            val sharedPreferences =
//                requireContext().getSharedPreferences("timer_prefs", Context.MODE_PRIVATE)
//            sharedPreferences.edit().putBoolean("isTimerActive", false).apply()
//            sharedPreferences.edit().remove("remainingSeconds").apply()
//            sharedPreferences.edit().remove("total_time").apply()
//
//            stopTimer()
//            goTimerSettingFragment()
//        }
//    }

    override fun onDestroy() {
        super.onDestroy()
        val sharedPreferences =
            requireContext().getSharedPreferences("timer_prefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().putBoolean("isTimerReActive", false).apply()
    }


    private fun getLeftTime() {
        // SharedPreferences에서 타이머 계산 결과를 읽어옴
        val sharedPreferences =
            requireContext().getSharedPreferences("timer_prefs", Context.MODE_PRIVATE)
        val totalSeconds = sharedPreferences.getLong("remainingSeconds", totalTime)
        viewModel.updateLeftTime(totalSeconds) // totalSeconds 값을 설정
        Log.d("aaa", "설마!")
    }

    private fun initCircularProgressBar() {
        val sharedPreferences =
            requireContext().getSharedPreferences("timer_prefs", Context.MODE_PRIVATE)
        val totalTime = sharedPreferences.getFloat("total_time", 0F)

        viewModel.setMaxTime(totalTime)
        Log.d("aaa", "찍어!")
        viewModel.leftTime.observe(viewLifecycleOwner) { time ->
            time?.let {
                Log.d("aaa", "여기도!")
                binding.circularProgressBar.progress = it.toFloat()
            }
        }
    }

    private fun timerEnd() {

        viewModel.leftTime.observe(viewLifecycleOwner) { time ->
            if (time == 0L) {
                timerTask.cancel()
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
