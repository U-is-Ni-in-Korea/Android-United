package sopt.uni.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import sopt.uni.data.datasource.local.SparkleStorage
import sopt.uni.data.repository.auth.AuthRepository
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val _loginResult: MutableStateFlow<InHouseLoginState> =
        MutableStateFlow(InHouseLoginState.Init)
    val loginResult: StateFlow<InHouseLoginState> = _loginResult.asStateFlow()

    fun getAccessToken(social: String, code: String) {
        viewModelScope.launch {
            authRepository.getToken(social, code)
                .onSuccess { token ->
                    SparkleStorage.accessToken = token.toToken().accessToken
                    _loginResult.value = InHouseLoginState.Success
                    Timber.tag("accessToken").d("getAccessToken with server: ${token.toToken().accessToken}")
                }.onFailure {
                    _loginResult.value = InHouseLoginState.Failure(it.message ?: "로그인에 실패했습니다.")
                }
        }
    }

    sealed class InHouseLoginState {
        object Init : InHouseLoginState()
        object Success : InHouseLoginState()
        data class Failure(val message: String) : InHouseLoginState()
    }
}
