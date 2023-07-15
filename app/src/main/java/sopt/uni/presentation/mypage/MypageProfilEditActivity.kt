package sopt.uni.presentation.mypage

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.databinding.BindingAdapter
import sopt.uni.R
import sopt.uni.databinding.ActivityMypageProfilEditBinding
import sopt.uni.util.binding.BindingActivity

class MypageProfilEditActivity :
    BindingActivity<ActivityMypageProfilEditBinding>(R.layout.activity_mypage_profil_edit) {

    private val viewModel: MypageProfilEditViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.viewModel = viewModel
    }

    companion object {
        private const val MAX_LENGTH = 10

        @JvmStatic
        @BindingAdapter("setMypageContentLength")
        fun setMypageContentLength(view: TextView, length: Int) {
            if (length >= MAX_LENGTH) {
                view.setTextColor(view.context.getColor(R.color.Red_500))
            } else {
                view.setTextColor(view.context.getColor(R.color.Gray_400))
            }
            view.text = "$length/$MAX_LENGTH"
        }

        @JvmStatic
        @BindingAdapter("setMypageErrorTextVisible")
        fun setMypageErrorTextVisible(view: TextView, length: Int) {
            if (length >= MAX_LENGTH) {
                view.visibility = View.VISIBLE
            } else {
                view.visibility = View.INVISIBLE
            }
        }

        @JvmStatic
        @BindingAdapter("setMypageContentLength")
        fun setMypageContentLength(view: EditText, length: Int) {
            if (length >= MAX_LENGTH) {
                view.background = view.context.getDrawable(R.drawable.bg_mypage_edit_text_error)
            } else {
                view.background = view.context.getDrawable(R.drawable.bg_mypage_edit_text)
            }
        }
    }
}
