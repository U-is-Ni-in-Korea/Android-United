package sopt.uni.presentation.mypage

import android.os.Bundle
import android.view.View
import sopt.uni.R
import sopt.uni.databinding.DatepickerDialogBinding
import sopt.uni.presentation.BindingDialogFragment
import sopt.uni.util.extension.setOnSingleClickListener

class MypageDatePickerDialogFragment :
    BindingDialogFragment<DatepickerDialogBinding>(
        R.layout.datepicker_dialog,
        isWrapContent = true,
    ) {
    private var listener: DatePickerDialogListener? = null

    fun setDatePickerDialogListener(listener: DatePickerDialogListener) {
        this.listener = listener
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLeft.setOnSingleClickListener {
            dismiss()
        }
        binding.btnRight.setOnSingleClickListener {
            val selectedDate = binding.datePicker.year.toString() +
                "년 " + (binding.datePicker.month + 1).toString() +
                "월 " + binding.datePicker.dayOfMonth.toString() +
                "일"

            listener?.onDateSelected(selectedDate)
            dismiss()
        }
    }
}
