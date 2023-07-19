package sopt.uni.presentation.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import dagger.hilt.android.lifecycle.HiltViewModel
import sopt.uni.data.entity.history.MyPage
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class MypageProfilEditViewModel @Inject constructor() : ViewModel() {

    private val _parsedStartDate = MutableLiveData<String>()
    val parsedStartDate: LiveData<String> = _parsedStartDate

    private val _mypageData = MutableLiveData<MyPage>()
    val mypageData: LiveData<MyPage> = _mypageData

    val mypageContent = MutableLiveData<String>("")

    val contentLength = mypageContent.map {
        it.length
    }

    fun setMyPage(mypage: MyPage) {
        _mypageData.value = mypage
    }

    fun parseStartDate(startDate: String): String {
        val parsedDate = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE)
        val formattedDate =
            parsedDate.format(DateTimeFormatter.ofPattern("yyyy년 M월 d일", Locale.getDefault()))
        return formattedDate
    }
}
