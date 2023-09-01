package sopt.uni.presentation.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import sopt.uni.data.entity.history.MyPage
import sopt.uni.data.repository.mypage.MypageRepository
import sopt.uni.util.UiState
import javax.inject.Inject

@HiltViewModel
class MypageSettingViewModel @Inject constructor(
    private val mypageRepository: MypageRepository,
) : ViewModel() {
    private val _myPageData = MutableLiveData<UiState<MyPage>>(UiState.Loading)
    val myPageData: LiveData<UiState<MyPage>> = _myPageData

    init {
        getMyPageInfo()
    }

    private fun getMyPageInfo() {
        viewModelScope.launch {
            kotlin.runCatching {
                val result = mypageRepository.getMyPageInfo()
                result.getOrThrow()
            }.fold(
                onSuccess = { mypageInfo ->
                    _myPageData.value = UiState.Success(mypageInfo)
                },
                onFailure = { throwable ->
                    _myPageData.value = throwable.message?.let { UiState.Failure(it) }
                },
            )
        }
    }
}
