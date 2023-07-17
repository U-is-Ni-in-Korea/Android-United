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
        binding.mypageAccountBack.setOnSingleClickListener {
            startActivity<MypageSettingActivity>()
            finish()
        }
    }

    private fun setupAccountList1() {
        binding.mypageAccountList1.setOnSingleClickListener {
            MypageAccountLogoutDialogFragment().show(supportFragmentManager, "MypageAccountLogoutDialogFragment")
        }
    }

    private fun setupAccountList2() {
        binding.mypageAccountList2.setOnSingleClickListener {
            MypageAccountDeleteDialogFragment().show(supportFragmentManager, "MypageAccountDeleteDialogFragment")
        }
    }

    private fun setupAccountList3() {
        binding.mypageAccountList3.setOnSingleClickListener {
            MypageAccountCoupleDisconnectDialogFragment().show(supportFragmentManager, "MypageAccountCoupleDisconnectDialogFragment")
        }
    }
}
