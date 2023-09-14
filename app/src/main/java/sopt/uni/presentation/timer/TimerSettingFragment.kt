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
            showSnackbar(binding.root, message.peekContent())
            vibrateSingle()
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

            initsharedPrefSetting(total)
            setWorkManager(total)
            goTimerActiveFragment(total)
        }
    }

    private fun goTimerActiveFragment(total: Int) {
        if (total == 0) {
            showSnackbar(binding.root, "시간을 설정해주세요")
        } else {
            val fragmentTimerActive = TimerActiveFragment(total.toFloat())
            val fragmentTransaction = parentFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fcv_timer, fragmentTimerActive)
            fragmentTransaction.commit()
        }
    }

    private fun setWorkManager(total: Int) {
        val data = Data.Builder()
            .putLong(TOTALTIMEKEY, total.toLong())
            .build()

        val timerWorkRequest = OneTimeWorkRequestBuilder<TimerWorker>()
            .setInputData(data)
            .build()

        val workManager = WorkManager.getInstance(requireContext())
        workManager.enqueue(timerWorkRequest)
    }

    private fun initsharedPrefSetting(total: Int) {
        val sharedPreferences =
            requireContext().getSharedPreferences(NAME, Context.MODE_PRIVATE)
        sharedPreferences.edit().putFloat(TOTALTIMEKEY, total.toFloat()).apply()
        sharedPreferences.edit().putBoolean(ACTIVEKEY, true).apply()
        sharedPreferences.edit().putBoolean(PAUSEKEY, false).apply()
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

    companion object {
        private const val NAME = "timer_prefs"
        private const val PAUSEKEY = "isTimerPause"
        private const val ACTIVEKEY = "isTimerActive"
        private const val TOTALTIMEKEY = "totalTime"
    }
}


