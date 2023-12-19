package sopt.uni.presentation.mypage

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import dagger.hilt.android.AndroidEntryPoint
import sopt.uni.R
import sopt.uni.databinding.ActivityMypageSettingBinding
import sopt.uni.presentation.WebViewActivity
import sopt.uni.util.UiState
import sopt.uni.util.binding.BindingActivity
import sopt.uni.util.extension.setOnSingleClickListener
import sopt.uni.util.extension.showSnackbar
import sopt.uni.util.extension.startActivity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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
        setupOSSLicenses()
        moveToTermsOfService()
        moveToPrivacyPolicy()
        moveToDeveloperInfo()
    }

    override fun onBackPressed() {
        finish()
    }

    private fun setupBackButton() {
        binding.btnMypageSettingBack.setOnSingleClickListener {
            finish()
        }
    }

    private fun setupServiceAccount() {
        binding.clMypageSettingServiceAccount.setOnSingleClickListener {
            startActivity<MypageAccountActivity>()
        }
    }

    private fun moveToWebView(url: Int) {
        Intent(this, WebViewActivity::class.java).apply {
            putExtra("url", resources.getString(url))
        }.run(::startActivity)
    }

    private fun moveToTermsOfService() {
        binding.clMypageSettingServiceInfo.setOnSingleClickListener {
            moveToWebView(R.string.service_url)
        }
    }

    private fun moveToPrivacyPolicy() {
        binding.clMypageSettingServicePrivacy.setOnSingleClickListener {
            moveToWebView(R.string.privacy_policy_url)
        }
    }

    private fun moveToDeveloperInfo() {
        binding.clMypageSettingServiceDeveloper.setOnSingleClickListener {
            moveToWebView(R.string.developer_info_url)
        }
    }

    private fun setupOSSLicenses() {
        binding.clMypageSettingServiceOpensource.setOnSingleClickListener {
            startActivity(Intent(this, OssLicensesMenuActivity::class.java))
        }
    }

    private fun observeMyPageData() {
        viewModel.myPageData.observe(this) { uiState ->
            when (uiState) {
                is UiState.Success -> {
                    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                    val date: Date = dateFormat.parse(uiState.data.startDate) as Date
                    val startDate =
                        SimpleDateFormat("yyyy.MM.dd (E)", Locale.getDefault()).format(date)

                    binding.tvMypageSettingName.text = uiState.data.nickname
                    binding.tvMypageSettingStartDate.text = startDate
                    binding.tvMypageSettingProfilEdit.setOnSingleClickListener {
                        val intent = Intent(this, MypageProfilEditActivity::class.java)
                        intent.putExtra(
                            MYPAGE,
                            requireNotNull(uiState.data),
                        )
                        startActivity(intent)
                    }
                }

                is UiState.Failure -> {
                    showSnackbar(binding.root, getString(R.string.mypage_setting_loading_fail_text))
                }

                else -> {}
            }
        }
    }

    companion object {
        const val MYPAGE = "mypage"
    }
}
