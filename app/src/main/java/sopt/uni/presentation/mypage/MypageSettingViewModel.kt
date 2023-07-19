package sopt.uni.presentation.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import sopt.uni.data.entity.history.MyPage
import sopt.uni.data.repository.mypage.MypageRepository
import javax.inject.Inject

@HiltViewModel
class MypageSettingViewModel @Inject constructor(
    private val mypageRepository: MypageRepository,
) : ViewModel() {
    private val _mypage = MutableLiveData<MyPage>()
    val mypage: LiveData<MyPage> = _mypage

    init {
        getMyPageInfo()
    }

    private fun getMyPageInfo() {
        viewModelScope.launch {
            kotlin.runCatching {
                val result = mypageRepository.getMyPageInfo()
                result.getOrNull()
            }.fold(
                onSuccess = { mypageInfo ->
                    _mypage.value = mypageInfo
                },
                onFailure = {
                },
            )
        }
    }
}
