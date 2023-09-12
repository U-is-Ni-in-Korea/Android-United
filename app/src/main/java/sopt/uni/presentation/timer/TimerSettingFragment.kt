package sopt.uni.presentation.timer

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import dagger.hilt.android.AndroidEntryPoint
import sopt.uni.R
import sopt.uni.databinding.FragmentTimerSettingBinding
import sopt.uni.util.MakeVibrator
import sopt.uni.util.binding.BindingFragment
import sopt.uni.util.extension.setOnSingleClickListener
import sopt.uni.util.extension.showSnackbar

@AndroidEntryPoint
class TimerSettingFragment :
    BindingFragment<FragmentTimerSettingBinding>(R.layout.fragment_timer_setting) {
    private val viewModel by activityViewModels<TimerViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initTimerSetting()
        setupStartButton()
        timerFinished()
    }

    private fun timerFinished() {
        viewModel.snackbarMessage.observe(viewLifecycleOwner) { message ->
            if (!message.isNullOrEmpty()) {
                showSnackbar(binding.root, message)
                vibrateSingle()
                // 스낵바 메시지를 한 번 표시한 후 다시 초기화
                viewModel.resetSnackbarMessage()
            }
        }
    }

    private fun vibrateSingle() {
        val vibrator = MakeVibrator()
        vibrator.init(requireContext())
        vibrator.make(2000)
    }

    private fun setupStartButton() {
        binding.btnTimerStart.setOnSingleClickListener {
            val minuteValue = binding.numberpickerMinute.value
            val secondValue = binding.numberpickerSeconds.value
            val total = minuteValue * 60 + secondValue


            val sharedPreferences =
                requireContext().getSharedPreferences("timer_prefs", Context.MODE_PRIVATE)
            sharedPreferences.edit().putFloat("total_time", total.toFloat()).apply()
            sharedPreferences.edit().putBoolean("isTimerActive", true).apply()
            sharedPreferences.edit().putBoolean("isTimerPause", false).apply()

            val data = Data.Builder()
                .putLong("totalSeconds", total.toLong())
                .build()

            val timerWorkRequest = OneTimeWorkRequestBuilder<TimerWorker>()
                .setInputData(data) // 타이머 시간을 Worker에 전달
                .addTag("timer_work_tag") // 태그를 지정
                .build()

            // WorkManager를 사용하여 Worker를 실행합니다.
            val workManager = WorkManager.getInstance(requireContext())
            workManager.enqueue(timerWorkRequest)

            val fragmentTimerActive = TimerActiveFragment(total.toFloat())
            val fragmentTransaction = parentFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fcv_timer, fragmentTimerActive)
            fragmentTransaction.commit()
        }
    }

    private fun initTimerSetting() {
        binding.numberpickerMinute.apply {
            minValue = 0
            maxValue = 59
            setFormatter { i ->
                String.format("%02d", i)
            }
        }

        binding.numberpickerSeconds.apply {
            minValue = 0
            maxValue = 59
            setFormatter { i ->
                String.format("%02d", i)
            }
        }
    }
}
