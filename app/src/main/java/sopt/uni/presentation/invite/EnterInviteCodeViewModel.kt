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
class EnterInviteCodeViewModel @Inject constructor(
    private val onBoardingRepository: OnBoardingRepository,
) : ViewModel() {

    val inviteCode = MutableStateFlow("")
    val connectState = MutableStateFlow("")

    fun checkCoupleConnection() {
        viewModelScope.launch {
            kotlin.runCatching {
                Timber.d("invite = ${inviteCode.value}")
                onBoardingRepository.postCheckConnection(inviteCode = inviteCode.value)
            }.fold({
                connectState.emit(it.code().toString())
                Timber.d("커플 연결 통신에 성공했습니다. code = ${it.code()}")
            }, {
                Timber.d(it)
            })
        }
    }
}
