package sopt.uni.presentation.invite

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import sopt.uni.R
import sopt.uni.databinding.ActivityEnterInviteCodeBinding
import sopt.uni.presentation.home.HomeActivity
import sopt.uni.util.binding.BindingActivity
import sopt.uni.util.extension.setOnSingleClickListener
import sopt.uni.util.extension.showToast
import sopt.uni.util.extension.startActivity

@AndroidEntryPoint
class EnterInviteCodeActivity :
    BindingActivity<ActivityEnterInviteCodeBinding>(R.layout.activity_enter_invite_code) {
    private val enterInviteCodeViewModel by viewModels<EnterInviteCodeViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = enterInviteCodeViewModel
        moveToPrevPage()
        checkInviteCode()
        changeBoxStrokeColor()
    }

    private fun moveToPrevPage() {
        binding.ivBackArrow.setOnSingleClickListener {
            finish()
        }
    }

    private fun changeBoxStrokeColor() {
        if (binding.etInviteCode.text.isNotBlank()) {
            binding.etInviteCode.background = getDrawable(R.drawable.bg_mypage_edit_text)
        }
    }

    private fun checkInviteCode() {
        binding.btnConnect.setOnSingleClickListener {
            enterInviteCodeViewModel.checkCoupleConnection()
            lifecycleScope.launch {
                enterInviteCodeViewModel.connectState.collect { responseCode ->
                    if (responseCode == "204") {
                        this@EnterInviteCodeActivity.showToast("커플 연결에 성공했습니다")
                        binding.etInviteCode.background =
                            getDrawable(R.drawable.bg_mypage_edit_text)
                        binding.tvInviteCodeErrorMessage.visibility = View.INVISIBLE
                        startActivity<HomeActivity>()
                        finishAffinity()
                    } else {
                        binding.etInviteCode.background =
                            getDrawable(R.drawable.bg_mypage_edit_text_error)
                        binding.tvInviteCodeErrorMessage.visibility = View.VISIBLE
                    }
                }
            }
        }
    }
}
