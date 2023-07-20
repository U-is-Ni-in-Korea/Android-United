package sopt.uni.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import sopt.uni.data.datasource.local.SparkleStorage
import sopt.uni.data.entity.home.HomeInfo
import sopt.uni.data.repository.home.HomeRepository
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
) : ViewModel() {

    val homeInfo = MutableStateFlow(HomeInfo())

    init {
        fetchHomeInfo()
    }

    fun fetchHomeInfo() {
        viewModelScope.launch {
            homeRepository.getHomeInfo().onSuccess {
                Timber.e(it.toString())
                SparkleStorage.userId = it.userId
                SparkleStorage.partnerId = it.partnerId
                Timber.e("${SparkleStorage.userId} ${SparkleStorage.partnerId}")
                homeInfo.value = it.toHomeInfo()
            }.onFailure {
                Timber.e(it)
            }
        }
    }
}
