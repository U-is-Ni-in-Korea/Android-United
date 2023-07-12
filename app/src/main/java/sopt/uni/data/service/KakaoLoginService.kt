package sopt.uni.data.service

import android.content.Context
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import dagger.hilt.android.qualifiers.ActivityContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import timber.log.Timber
import javax.inject.Inject

class KakaoLoginService @Inject constructor(
    @ActivityContext private val context: Context,
    private val client: UserApiClient,
) {

    val isKakaoTalkLoginAvailable: Boolean
        get() = client.isKakaoTalkLoginAvailable(context)
    private val _loginState: MutableStateFlow<LoginState> = MutableStateFlow(LoginState.Init)
    val loginState: StateFlow<LoginState> = _loginState.asStateFlow()
    private val kakaoAuthCallback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        Timber.e("error : $error, token : $token")
        error?.let(::handleLoginError)
        token?.let(::handleLoginSuccess)
    }

    fun loginByKakaoTalk() {
        client.loginWithKakaoTalk(context, callback = kakaoAuthCallback)
    }

    fun loginByKakaoAccount() {
        client.loginWithKakaoAccount(context, callback = kakaoAuthCallback)
    }

    private fun handleLoginError(throwable: Throwable) {
        _loginState.value = LoginState.Failure(throwable)
    }

    private fun handleLoginSuccess(oAuthToken: OAuthToken) {
        client.me { user, _ ->
            _loginState.value = LoginState.Success(oAuthToken.accessToken, user?.id.toString())
        }
    }

//    로그아웃 구현 시 추가 예정
//    fun logout() {
//        client.logout(Timber::e)
//    }

    sealed class LoginState {
        object Init : LoginState()
        data class Success(val token: String, val id: String) : LoginState()
        data class Failure(val error: Throwable) : LoginState()
    }
}
