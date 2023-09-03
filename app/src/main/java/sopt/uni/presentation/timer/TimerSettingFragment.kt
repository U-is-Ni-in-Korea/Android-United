package sopt.uni.presentation.timer

import android.os.Bundle
import android.view.View
import sopt.uni.R
import sopt.uni.databinding.FragmentTimerSettingBinding
import sopt.uni.util.binding.BindingFragment
import sopt.uni.util.extension.setOnSingleClickListener

class TimerSettingFragment :
    BindingFragment<FragmentTimerSettingBinding>(R.layout.fragment_timer_setting) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initTimerSetting()
        setupStartButton()
    }

    private fun setupStartButton() {
        binding.btnTimerStart.setOnSingleClickListener {
            val fragmentTimerActive = TimerActiveFragment()
            val fragmentTransaction = parentFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fcv_timer, fragmentTimerActive)
            fragmentTransaction.commit()
        }
    }

    private fun initTimerSetting() {
        val numberPickerMin = binding.numberpickerMinute
        val numberPickerSec = binding.numberpickerSeconds


        numberPickerMin.apply {
            minValue = 0
            maxValue = 59
            value = 5
            setFormatter { i ->
                String.format("%02d", i)
            }
        }

        numberPickerSec.apply {
            minValue = 0
            maxValue = 59
            value = 0
            setFormatter { i ->
                String.format("%02d", i)
            }
        }
    }
}