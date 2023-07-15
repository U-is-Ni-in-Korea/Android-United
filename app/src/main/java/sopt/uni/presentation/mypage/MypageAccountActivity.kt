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

        with(binding) {
            mypageSettingBack.setOnSingleClickListener {
                startActivity<MypageSettingActivity>()
                finish()
            }
            mypageSettingAccountList1.setOnSingleClickListener {
                MypageAccountLogoutDialogFragment().show(supportFragmentManager, "MypageAccountLogoutDialogFragment")
            }
            mypageSettingAccountList2.setOnSingleClickListener {
                MypageAccountDeleteDialogFragment().show(supportFragmentManager, "MypageAccountDeleteDialogFragment")
            }
            mypageSettingAccountList3.setOnSingleClickListener {
                MypageAccountCoupleDisconnectDialogFragment().show(supportFragmentManager, "MypageAccountCoupleDisconnectDialogFragment")
            }
        }
    }
}
