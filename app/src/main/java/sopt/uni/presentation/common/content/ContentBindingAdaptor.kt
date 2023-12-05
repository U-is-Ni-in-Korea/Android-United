package sopt.uni.presentation.common.content

import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.BindingAdapter
import sopt.uni.R

@BindingAdapter("setNicknameErrorMessage")
fun setNicknameErrorMessage(view: TextView, length: Int) {
    if (length in 0..5) {
        view.visibility = View.INVISIBLE
    } else {
        view.text = "글자 수를 초과했어요"
        view.visibility = View.VISIBLE
    }
}

@BindingAdapter("setNicknameContentLength")
fun setNicknameContentLength(view: EditText, length: Int) {
    if (length > MAX_LENGTH) {
        view.background = view.context.getDrawable(R.drawable.bg_mypage_edit_text_error)
    } else if (length == 0) {
        view.background = view.context.getDrawable(R.drawable.bg_enter_edit_text_empty)
    } else {
        view.background = view.context.getDrawable(R.drawable.bg_mypage_edit_text)
    }
}
