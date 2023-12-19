package sopt.uni.presentation.mypage

import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import sopt.uni.R
import sopt.uni.databinding.ActivityMypageAccountBinding
import sopt.uni.util.binding.BindingActivity
import sopt.uni.util.extension.setOnSingleClickListener

@AndroidEntryPoint
class MypageAccountActivity :
    BindingActivity<ActivityMypageAccountBinding>(R.layout.activity_mypage_account) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupBackButton()
        logoutUser()
        secessionUser()
        disconnectCouple()
    }

    private fun setupBackButton() {
        binding.btnMypageAccountBack.setOnSingleClickListener {
            finish()
        }
    }

    private fun logoutUser() {
        binding.clMypageAccountLogout.setOnSingleClickListener {
            MypageAccountLogoutDialogFragment().show(
                supportFragmentManager,
                "MypageAccountLogoutDialogFragment",
            )
        }
    }

    private fun secessionUser() {
        binding.clMypageAccountSecession.setOnSingleClickListener {
            MypageAccountDeleteDialogFragment().show(
                supportFragmentManager,
                "MypageAccountSecessionDialogFragment",
            )
        }
    }

    private fun disconnectCouple() {
        binding.clMypageAccountDisconnect.setOnSingleClickListener {
            MypageAccountCoupleDisconnectDialogFragment().show(
                supportFragmentManager,
                "MypageAccountCoupleDisconnectDialogFragment",
            )
        }
    }
}
