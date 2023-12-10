package sopt.uni.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
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

    private val _loginResult: MutableStateFlow<SparkleLoginState> =
        MutableStateFlow(SparkleLoginState.Init)
    val loginResult: StateFlow<SparkleLoginState> = _loginResult.asStateFlow()

    fun getAccessToken(social: String, code: String): Job {
        return viewModelScope.launch {
            authRepository.getToken(social, code)
                .onSuccess { authDto ->
                    SparkleStorage.accessToken = authDto.accessToken
                    Timber.tag("accessToken")
                        .d("getAccessToken with server: ${authDto.accessToken}")

                    if (authDto.coupleId == null) {
                        _loginResult.value = SparkleLoginState.Success
                    } else {
                        _loginResult.value = SparkleLoginState.AlreadyCoupled
                    }
                }.onFailure {
                    _loginResult.value = SparkleLoginState.Failure(it.message ?: "로그인에 실패했습니다.")
                }
        }
    }

    sealed class SparkleLoginState {
        object Init : SparkleLoginState()
        object Success : SparkleLoginState()
        data class Failure(val message: String) : SparkleLoginState()
        object AlreadyCoupled : SparkleLoginState()
    }
}
