package sopt.uni.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import sopt.uni.data.datasource.local.SparkleStorage
import sopt.uni.data.entity.home.HomeInfo
import sopt.uni.data.repository.home.HomeRepository
import sopt.uni.data.repository.shortgame.ShortGameRepository
import sopt.uni.presentation.common.content.ErrorCodeState
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
    private val shortRepository: ShortGameRepository,
) : ViewModel() {

    val homeInfo = MutableStateFlow(HomeInfo())

    private var _roundGameId = MutableStateFlow<Int?>(0)
    val roundGameId get() = _roundGameId.asStateFlow()

    private var _shortGameEnabled = MutableStateFlow(false)
    val shortGameEnabled get() = _shortGameEnabled.asStateFlow()

    private var _roundResult = MutableLiveData("")
    val roundResult: LiveData<String> = _roundResult

    private val _errorState = MutableStateFlow<ErrorCodeState>(ErrorCodeState.NoError)
    val errorState = _errorState.asStateFlow()

    init {
        fetchHomeInfo()
    }

    fun fetchHomeInfo(): Job {
        return viewModelScope.launch {
            homeRepository.getHomeInfo().onSuccess {
                Timber.e(it.toString())
                SparkleStorage.userId = it.userId
                SparkleStorage.partnerId = it.partnerId
                homeInfo.value = it.toHomeInfo()
                _roundGameId.value = it.roundGameId
                _shortGameEnabled.value = it.shortGame?.enable ?: false
                Timber.e("viewmodel's shortGame : ${it.shortGame}")
            }.onFailure { errorCode ->
                Timber.e("Exception : $errorCode")
                if (errorCode is Exception && errorCode.message.toString() == "UE1008") {
                    Timber.e("No Token")
                    _errorState.value = ErrorCodeState.NoToken
                }
            }
        }
    }

    fun getShortGameResult() {
        viewModelScope.launch {
            shortRepository.getShortGameResult(roundGameId.value!!).onSuccess {
                Timber.e(roundGameId.value.toString())
                _roundResult.value = it.myRoundMission.result
            }.onFailure {
                Timber.e(it)
            }
        }
    }
}
