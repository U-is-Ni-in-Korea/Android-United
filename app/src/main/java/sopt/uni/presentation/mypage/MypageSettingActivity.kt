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
        setupServiceList1()
        setupSettingProfilEdit()
    }

    private fun setupBackButton() {
        binding.mypageSettingBack.setOnSingleClickListener {
            startActivity<HomeActivity>()
            finish()
        }
    }

    private fun setupServiceList1() {
        binding.mypageSettingServiceList1.setOnSingleClickListener {
            startActivity<MypageAccountActivity>()
            finish()
        }
    }

    private fun setupSettingProfilEdit() {
        binding.mypageSettingProfilEdit.setOnSingleClickListener {
            startActivity<MypageProfilEditActivity>()
            finish()
        }
    }
}
