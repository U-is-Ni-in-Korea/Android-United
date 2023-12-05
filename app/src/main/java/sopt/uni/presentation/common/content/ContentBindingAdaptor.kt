package sopt.uni.presentation.common.content

import android.widget.EditText
import androidx.databinding.BindingAdapter
import sopt.uni.R

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
