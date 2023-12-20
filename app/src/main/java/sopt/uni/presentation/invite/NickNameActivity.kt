package sopt.uni.presentation.invite

import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.MotionEvent
import android.view.MotionEvent.ACTION_DOWN
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import sopt.uni.R
import sopt.uni.databinding.ActivityNicknameBinding
import sopt.uni.presentation.login.LoginActivity
import sopt.uni.presentation.mypage.MypageAccountLogoutDialogFragment
import sopt.uni.util.binding.BindingActivity
import sopt.uni.util.extension.setOnSingleClickListener
import sopt.uni.util.extension.startActivity

@AndroidEntryPoint
class NickNameActivity : BindingActivity<ActivityNicknameBinding>(R.layout.activity_nickname) {
    private val nickNameViewModel by viewModels<NickNameViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewModel = nickNameViewModel

        moveToInviteHub()
        moveToPrevPage()
        moveToAskPage()
        logoutOnNickNamePage()
        initOnBackPressedListener()
    }

    private fun moveToInviteHub() {
        binding.btnNext.setOnSingleClickListener() {
            startActivity<InviteHubActivity>()
            nickNameViewModel.patchNickName(binding.etNickname.text.toString())
        }
    }

    private fun moveToPrevPage() {
        binding.ivBackArrow.setOnSingleClickListener {
            backToLogin()
        }
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

    private fun moveToAskPage() {
        binding.tvAsk.setOnSingleClickListener {
            startActivity<AskActivity>()
        }
    }

    private fun logoutOnNickNamePage() {
        binding.tvNicknameLogout.setOnSingleClickListener {
            MypageAccountLogoutDialogFragment().show(
                supportFragmentManager,
                "MypageAccountLogoutDialogFragment",
            )
        }
    }

    private fun initOnBackPressedListener() {
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                backToLogin()
            }
        }
        this.onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    private fun backToLogin() {
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity<LoginActivity>()
        finish()
    }
}
