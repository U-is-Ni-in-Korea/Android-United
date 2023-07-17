package sopt.uni.presentation.invite

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.MotionEvent
import android.view.MotionEvent.ACTION_DOWN
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import sopt.uni.R
import sopt.uni.databinding.ActivityNicknameBinding
import sopt.uni.util.binding.BindingActivity
import sopt.uni.util.extension.setOnSingleClickListener
import sopt.uni.util.extension.startActivity

@AndroidEntryPoint
class NickNameActivity : BindingActivity<ActivityNicknameBinding>(R.layout.activity_nickname) {
    private val nickNameViewModel by viewModels<NickNameViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.viewModel = nickNameViewModel

        patchNickName()
        moveToInviteHub()
        moveToPrevPage()
    }

    private fun moveToInviteHub() {
        binding.btnNext.setOnSingleClickListener() {
            startActivity<InviteHubActivity>()
        }
    }

    private fun moveToPrevPage() {
        binding.ivBackArrow.setOnSingleClickListener {
            finish()
        }
    }

    private fun patchNickName(){
        nickNameViewModel.patchNickName(nickNameViewModel.nickName.value)
    }

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        if (event?.action === ACTION_DOWN) {
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
}
