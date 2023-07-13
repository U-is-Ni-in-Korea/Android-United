package sopt.uni.presentation.login

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private val _loginResult: MutableStateFlow<InHouseLoginState> =
        MutableStateFlow(InHouseLoginState.Init)
    val loginResult: StateFlow<InHouseLoginState> = _loginResult.asStateFlow()

//    fun login(token: String, id: String) {
//        viewModelScope.launch {
//            runCatching { authRepository.login(token, id) }
//                .fold({
//                    _loginResult.value = InHouseLoginState.Success
//                }, {
//                    _loginResult.value = InHouseLoginState.Failure(it.message ?: "로그인에 실패했습니다")
//                    Timber.e(it)
//                })
//        }
//    }

    sealed class InHouseLoginState {
        object Init : InHouseLoginState()
        object Success : InHouseLoginState()
        data class Failure(val message: String) : InHouseLoginState()
    }
}
