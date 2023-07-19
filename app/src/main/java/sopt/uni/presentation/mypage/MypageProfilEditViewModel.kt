package sopt.uni.presentation.mypage

import ContentUriRequestBody
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import sopt.uni.data.entity.history.MyPage
import sopt.uni.data.repository.mypage.MypageRepository
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MypageProfilEditViewModel @Inject constructor(
    private val mypageRepository: MypageRepository,
) : ViewModel() {

    private val _image = MutableLiveData<ContentUriRequestBody>()
    val image: LiveData<ContentUriRequestBody>
        get() = _image

    private val _mypageData = MutableLiveData<MyPage>()
    val mypageData: LiveData<MyPage> = _mypageData

    val mypageContent = MutableLiveData<String>("")

    val contentLength = mypageContent.map {
        it.length
    }

    fun setMyPage(mypage: MyPage) {
        _mypageData.value = mypage
    }

    fun setNickname(nickname: String) {
        mypageContent.value = nickname
    }

    fun setRequestBody(requestBody: ContentUriRequestBody) {
        _image.value = requestBody
    }

    fun saveProfile(nickname: String, startDate: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                val result = mypageRepository.sendMyPageInfo(
                    image.value?.toFormData(),
                    nickname,
                    startDate,
                )
                result.getOrNull()
            }.fold(
                onSuccess = {
                    Timber.d("성공")
                },
                onFailure = {
                    Timber.d("실패")
                },
            )
        }
    }
}
