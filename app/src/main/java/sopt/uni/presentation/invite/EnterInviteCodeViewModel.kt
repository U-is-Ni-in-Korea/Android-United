package sopt.uni.presentation.invite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import sopt.uni.data.repository.onboarding.OnBoardingRepository
import sopt.uni.util.UiState
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class EnterInviteCodeViewModel @Inject constructor(
    private val onBoardingRepository: OnBoardingRepository,
) : ViewModel() {

    val inviteCode = MutableStateFlow("")

    private var _connectState = MutableStateFlow<UiState<String>>(UiState.Loading)
    val connectState get() = _connectState.asStateFlow()

    fun checkCoupleConnection() {
        viewModelScope.launch {
            kotlin.runCatching {
                Timber.d("invite = ${inviteCode.value}")
                onBoardingRepository.postCheckConnection(inviteCode = inviteCode.value)
            }.fold({
                _connectState.value = UiState.Success(it.code().toString())
            }, {
                Timber.d(it)
            })
        }
    }
}
