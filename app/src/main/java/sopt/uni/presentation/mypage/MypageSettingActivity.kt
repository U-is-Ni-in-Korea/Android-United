package sopt.uni.presentation.mypage

import android.os.Bundle
import sopt.uni.R
import sopt.uni.databinding.ActivityMypageSettingBinding
import sopt.uni.presentation.home.HomeActivity
import sopt.uni.util.binding.BindingActivity
import sopt.uni.util.extension.setOnSingleClickListener
import sopt.uni.util.extension.startActivity

class MypageSettingActivity :
    BindingActivity<ActivityMypageSettingBinding>(R.layout.activity_mypage_setting) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupBackButton()
        setupServiceAccount()
        setupSettingProfilEdit()
    }

    private fun setupBackButton() {
        binding.btnMypageSettingBack.setOnSingleClickListener {
            startActivity<HomeActivity>()
            finish()
        }
    }

    private fun setupServiceAccount() {
        binding.clMypageSettingServiceAccount.setOnSingleClickListener {
            startActivity<MypageAccountActivity>()
            finish()
        }
    }

    private fun setupSettingProfilEdit() {
        binding.tvMypageSettingProfilEdit.setOnSingleClickListener {
            startActivity<MypageProfilEditActivity>()
            finish()
        }
    }
}
