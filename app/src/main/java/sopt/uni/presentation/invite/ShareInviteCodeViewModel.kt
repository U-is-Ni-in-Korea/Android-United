package sopt.uni.presentation.invite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import sopt.uni.data.repository.onboarding.OnBoardingRepository
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ShareInviteCodeViewModel @Inject constructor(
    private val onBoardingRepository: OnBoardingRepository,
) : ViewModel() {
    val isConnected = MutableStateFlow<Boolean>(false)

    fun checkCoupleConnection() {
        viewModelScope.launch {
            kotlin.runCatching {
                onBoardingRepository.checkCoupleConnection()
            }.fold({
                isConnected.emit(it)
            }, {
                Timber.d(it)
            })
        }
    }
}
