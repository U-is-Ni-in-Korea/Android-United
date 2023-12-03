package sopt.uni.presentation.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import sopt.uni.data.repository.mypage.MypageRepository

class MyPageAccountViewModel(
    private val mypageRepository: MypageRepository,
) : ViewModel() {

    fun deleteUser() {
        viewModelScope.launch {
            mypageRepository.deleteUser()
        }
    }

    fun disconnectCouple() {
        viewModelScope.launch {
            mypageRepository.disconnectCouple()
        }
    }
}
