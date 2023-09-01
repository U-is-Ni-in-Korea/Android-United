package sopt.uni.presentation.mypage

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import sopt.uni.R
import sopt.uni.databinding.ActivityMypageSettingBinding
import sopt.uni.presentation.home.HomeActivity
import sopt.uni.util.UiState
import sopt.uni.util.binding.BindingActivity
import sopt.uni.util.extension.setOnSingleClickListener
import sopt.uni.util.extension.startActivity

@AndroidEntryPoint
class MypageSettingActivity :
    BindingActivity<ActivityMypageSettingBinding>(R.layout.activity_mypage_setting) {
    private val viewModel: MypageSettingViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupBackButton()
        setupServiceAccount()
        observeMyPageData()
    }

    override fun onBackPressed() {
        finish()
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
        }
    }

    private fun observeMyPageData() {
        viewModel.myPageData.observe(this) { uiState ->
            when (uiState) {
                is UiState.Success -> {
                    binding.tvMypageSettingName.text = uiState.data.nickname
                    binding.tvMypageSettingProfilEdit.setOnSingleClickListener {
                        val intent = Intent(this, MypageProfilEditActivity::class.java)
                        intent.putExtra(
                            MYPAGE,
                            requireNotNull(uiState.data),
                        )
                        startActivity(intent)
                    }
                }

                else -> {}
            }
        }
    }

    companion object {
        const val MYPAGE = "mypage"
    }
}
