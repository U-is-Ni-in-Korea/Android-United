package sopt.uni.presentation.mypage

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.databinding.BindingAdapter
import sopt.uni.R
import sopt.uni.databinding.ActivityMypageProfilEditBinding
import sopt.uni.util.binding.BindingActivity
import sopt.uni.util.extension.setOnSingleClickListener
import sopt.uni.util.extension.startActivity

class MypageProfilEditActivity :
    BindingActivity<ActivityMypageProfilEditBinding>(R.layout.activity_mypage_profil_edit) {

    private val viewModel: MypageProfilEditViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val datePickerDialogFragment = MypageDatePickerDialogFragment()
        binding.viewModel = viewModel

        setupDatePicker(datePickerDialogFragment)
        setupBackButton()
        setupSaveButton()
        setupImageChangeButton()
    }

    private fun setupDatePicker(datePickerDialogFragment: MypageDatePickerDialogFragment) {
        datePickerDialogFragment.setDatePickerDialogListener(object : DatePickerDialogListener {
            override fun onDateSelected(date: String) {
                binding.tvMypageProfilEditCoupleDate.setText(date)
            }
        })

        binding.tvMypageProfilEditCoupleDate.setOnSingleClickListener {
            datePickerDialogFragment.show(supportFragmentManager, "MyPageDatePickerDialog")
        }
    }

    private fun setupBackButton() {
        binding.btnMypageProfilEditBack.setOnSingleClickListener {
            startActivity<MypageSettingActivity>()
            finish()
        }
    }

    private fun setupSaveButton() {
        binding.tvMypageProfilEditSave.setOnSingleClickListener {
            // 프로필 저장 처리
        }
    }

    private fun setupImageChangeButton() {
        binding.tvMypageProfilEditImageChange.setOnSingleClickListener {
            // 프로필 이미지 바꾸기
        }
    }

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        if (event?.action === MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (v is EditText) {
                val outRect = Rect()
                v.getGlobalVisibleRect(outRect)
                if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                    v.clearFocus()
                    val imm: InputMethodManager =
                        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.windowToken, 0)
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }

    companion object {
        private const val MAX_LENGTH = 10

        @JvmStatic
        @BindingAdapter("setMypageContentLength")
        fun setMypageContentLength(view: TextView, length: Int) {
            if (length >= MAX_LENGTH || length == 0) {
                view.setTextColor(view.context.getColor(R.color.Red_500))
            } else {
                view.setTextColor(view.context.getColor(R.color.Gray_400))
            }
            view.text = "$length/$MAX_LENGTH"
        }

        @JvmStatic
        @BindingAdapter("setMypageOverErrorTextVisible")
        fun setMypageOverErrorTextVisible(view: TextView, length: Int) {
            if (length >= MAX_LENGTH) {
                view.visibility = View.VISIBLE
            } else {
                view.visibility = View.INVISIBLE
            }
        }

        @JvmStatic
        @BindingAdapter("setMypageZeroErrorTextVisible")
        fun setMypageZeroErrorTextVisible(view: TextView, length: Int) {
            if (length == 0) {
                view.visibility = View.VISIBLE
            } else {
                view.visibility = View.INVISIBLE
            }
        }

        @JvmStatic
        @BindingAdapter("setMypageContentLength")
        fun setMypageContentLength(view: EditText, length: Int) {
            if (length >= MAX_LENGTH || length == 0) {
                view.background = view.context.getDrawable(R.drawable.bg_mypage_edit_text_error)
            } else {
                view.background = view.context.getDrawable(R.drawable.bg_mypage_edit_text)
            }
        }
    }
}
