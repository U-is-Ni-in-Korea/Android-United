package sopt.uni.presentation.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import dagger.hilt.android.lifecycle.HiltViewModel
import sopt.uni.data.entity.history.MyPage
import javax.inject.Inject

@HiltViewModel
class MypageProfilEditViewModel @Inject constructor() : ViewModel() {

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
}
