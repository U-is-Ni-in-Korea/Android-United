package sopt.uni.presentation.common.content

import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.BindingAdapter
import sopt.uni.R

@BindingAdapter("nicknameErrorMessageVisibility")
fun setNicknameErrorMessageVisibility(view: TextView, length: Int) {
    if (length in 0..MAX_LENGTH) {
        view.visibility = View.INVISIBLE
    } else {
        view.visibility = View.VISIBLE
    }
}

@BindingAdapter("nicknameFieldStrokeColor")
fun setNicknameFieldStrokeColor(view: EditText, length: Int) {
    if (length > MAX_LENGTH) {
        view.background = view.context.getDrawable(R.drawable.bg_mypage_edit_text_error)
    } else if (length == 0) {
        view.background = view.context.getDrawable(R.drawable.bg_enter_edit_text_empty)
    } else {
        view.background = view.context.getDrawable(R.drawable.bg_mypage_edit_text)
    }
}
