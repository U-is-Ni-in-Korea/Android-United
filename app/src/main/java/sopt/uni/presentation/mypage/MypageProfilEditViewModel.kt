package sopt.uni.presentation.mypage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map

class MypageProfilEditViewModel : ViewModel() {

    val mypageContent = MutableLiveData<String>("")

    val contentLength = mypageContent.map {
        it.length
    }
}
