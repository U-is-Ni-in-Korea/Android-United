package sopt.uni.presentation.mypage

import android.os.Bundle
import android.view.View
import sopt.uni.R
import sopt.uni.databinding.DatepickerDialogBinding
import sopt.uni.util.binding.BindingDialogFragment
import sopt.uni.util.extension.setOnSingleClickListener
import java.util.Calendar

class MypageDatePickerDialogFragment :
    BindingDialogFragment<DatepickerDialogBinding>(
        R.layout.datepicker_dialog,
    ) {
    private var listener: DatePickerDialogListener? = null

    fun setDatePickerDialogListener(listener: DatePickerDialogListener) {
        this.listener = listener
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setLayoutSizeRatio(0.77f, 0.34f)

        setMaxDate()

        binding.btnLeft.setOnSingleClickListener {
            dismiss()
        }
        binding.btnRight.setOnSingleClickListener {
            val selectedDate = binding.datePicker.year.toString() +
                getString(R.string.year) + (binding.datePicker.month + 1).toString() +
                getString(R.string.month) + binding.datePicker.dayOfMonth.toString() +
                getString(R.string.day)

            listener?.onDateSelected(selectedDate)
            dismiss()
        }
    }

    private fun setMaxDate() {
        val calendar = Calendar.getInstance()
        binding.datePicker.maxDate = calendar.timeInMillis
    }
}
