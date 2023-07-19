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
class DdayViewModel @Inject constructor(
    private val onBoardingRepository: OnBoardingRepository,
) : ViewModel() {

    var inviteCode = MutableStateFlow("")

    fun postStartDate(startDate: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                onBoardingRepository.postStartDate(startDate = startDate)
            }.fold({
                inviteCode.emit(it)
                Timber.d("기념일 갱신이 완료되었습니다.")
            }, {
                Timber.d("기념일 갱신이 실패했습니다.")
            })
        }
    }
}
