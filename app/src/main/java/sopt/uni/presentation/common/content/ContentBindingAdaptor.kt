package sopt.uni.presentation.common.content

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("setNicknameErrorMessage")
fun setNicknameErrorMessage(view: TextView, length: Int) {
    if (length == 0) {
        view.visibility = View.VISIBLE
    } else if (length in 1..10) {
        view.visibility = View.INVISIBLE
    } else {
        view.text = "글자 수를 초과했어요"
        view.visibility = View.VISIBLE
    }
}
