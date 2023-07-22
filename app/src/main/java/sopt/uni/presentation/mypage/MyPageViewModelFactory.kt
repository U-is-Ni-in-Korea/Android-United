package sopt.uni.presentation.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import sopt.uni.data.repository.mypage.MypageRepositoryImpl
import sopt.uni.data.service.MyPageService

class MyPageAccountViewModelFactory(
    private val myPageService: MyPageService,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MyPageAccountViewModel::class.java) -> {
                val repository = MypageRepositoryImpl(myPageService)
                MyPageAccountViewModel(repository) as T
            }

            else -> {
                throw IllegalArgumentException("Failed to create ViewModel: ${modelClass.name}")
            }
        }
    }
}
