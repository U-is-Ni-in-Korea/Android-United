package sopt.uni.presentation.invite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import sopt.uni.data.datasource.local.SparkleStorage
import sopt.uni.data.repository.onboarding.OnBoardingRepository
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DdayViewModel @Inject constructor(
    private val onBoardingRepository: OnBoardingRepository,
) : ViewModel() {

    private val _inviteCode = MutableStateFlow<String>("")
    val inviteCode get() = _inviteCode.asStateFlow()

    fun postStartDate(startDate: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                onBoardingRepository.postStartDate(startDate = startDate)
            }.fold({
                it.body()?.let { response ->
                    Timber.d("기념일 갱신이 성공했습니다.")
                    _inviteCode.value = response.inviteCode
                    SparkleStorage.inviteCode = response.inviteCode
                    SparkleStorage.coupleId = response.coupleId
                } ?: run {
                    Timber.d("기념일 갱신에서 null이 반환되었습니다.")
                }
            }, {
                Timber.d("기념일 갱신이 실패했습니다.")
            })
        }
    }
}
