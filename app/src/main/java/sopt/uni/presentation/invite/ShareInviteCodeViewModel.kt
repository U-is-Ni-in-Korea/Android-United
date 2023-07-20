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
class ShareInviteCodeViewModel @Inject constructor(
    private val onBoardingRepository: OnBoardingRepository,
) : ViewModel() {

    private var _connectedState = MutableStateFlow<UiState<Boolean>>(UiState.Loading)
    val connectedState get() = _connectedState.asStateFlow()

    fun checkCoupleConnection() {
        viewModelScope.launch {
            kotlin.runCatching {
                onBoardingRepository.checkCoupleConnection()
            }.fold({
                _connectedState.value = UiState.Success(it)
            }, {
                _connectedState.value = UiState.Failure(it.message ?: "Cannot find error message")
            })
        }
    }
}
