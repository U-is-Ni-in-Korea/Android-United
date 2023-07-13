package sopt.uni.presentation.login

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.kakao.sdk.common.util.Utility
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import sopt.uni.R
import sopt.uni.data.service.KakaoLoginService
import sopt.uni.databinding.ActivityLoginBinding
import sopt.uni.presentation.invite.NickNameActivity
import sopt.uni.util.binding.BindingActivity
import sopt.uni.util.extension.setOnSingleClickListener
import sopt.uni.util.extension.startActivity
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : BindingActivity<ActivityLoginBinding>(R.layout.activity_login) {
    @Inject
    lateinit var kakaoLoginService: KakaoLoginService
    private val loginViewModel by viewModels<LoginViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val keyHash = Utility.getKeyHash(this)
        Timber.d(keyHash)

        moveToKakaoLogin()
        collectIsTokenAvailability()
    }

    private fun collectIsTokenAvailability() {
        lifecycleScope.launch {
            kakaoLoginService.loginState
                .flowWithLifecycle(lifecycle)
                .collect {
                    when (it) {
                        is KakaoLoginService.LoginState.Success -> {
                            startActivity<NickNameActivity>()
                            Timber.e("Kakao Login Success ${it.token} ${it.id}")
                        }

                        is KakaoLoginService.LoginState.Failure -> Timber.d("Kakao Login Failed ${it.error}")
                        else -> Timber.d("Kakao INIT")
                    }
                }
        }
    }

    private fun moveToKakaoLogin() {
        binding.ivKakaoLogin.setOnSingleClickListener {
            if (kakaoLoginService.isKakaoTalkLoginAvailable) {
                kakaoLoginService.loginByKakaoTalk()
            } else {
                kakaoLoginService.loginByKakaoAccount()
            }
        }
    }
}
