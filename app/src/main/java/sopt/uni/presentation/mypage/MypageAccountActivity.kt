package sopt.uni.presentation.mypage

import android.os.Bundle
import sopt.uni.R
import sopt.uni.databinding.ActivityMypageAccountBinding
import sopt.uni.util.binding.BindingActivity
import sopt.uni.util.extension.setOnSingleClickListener
import sopt.uni.util.extension.startActivity

class MypageAccountActivity :
    BindingActivity<ActivityMypageAccountBinding>(R.layout.activity_mypage_account) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupBackButton()
        setupAccountList1()
        setupAccountList2()
        setupAccountList3()
    }

    private fun setupBackButton() {
        binding.btnMypageAccountBack.setOnSingleClickListener {
            startActivity<MypageSettingActivity>()
            finish()
        }
    }

    private fun setupAccountList1() {
        binding.tvMypageAccountLogout.setOnSingleClickListener {
            MypageAccountLogoutDialogFragment().show(supportFragmentManager, "MypageAccountLogoutDialogFragment")
        }
    }

    private fun setupAccountList2() {
        binding.tvMypageAccountSecession.setOnSingleClickListener {
            MypageAccountDeleteDialogFragment().show(supportFragmentManager, "MypageAccountSecessionDialogFragment")
        }
    }

    private fun setupAccountList3() {
        binding.tvMypageAccountDisconnect.setOnSingleClickListener {
            MypageAccountCoupleDisconnectDialogFragment().show(supportFragmentManager, "MypageAccountCoupleDisconnectDialogFragment")
        }
    }
}
