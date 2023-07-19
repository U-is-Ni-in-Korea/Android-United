package sopt.uni.presentation.shortgame.missionrecord

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import sopt.uni.data.entity.shortgame.RoundMission
import sopt.uni.data.repository.shortgame.ShortGameRepository
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MissionRecordViewModel @Inject constructor(
    private val shortGameRepository: ShortGameRepository,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    val roundGameId: Int = savedStateHandle.get<Int>(MissionRecordActivity.ROUND_GAME_ID) ?: -1

    private val _isMissionDeleteSuccess = MutableLiveData<Boolean>(false)
    val isMissionDeleteSuccess = _isMissionDeleteSuccess

    private val _isMissionRequestSuccess = MutableLiveData<Boolean>(false)
    val isMissionRequestSuccess = _isMissionRequestSuccess

    private val _myMissionResult = MutableLiveData<RoundMission>()
    val myMissionResult = _myMissionResult

    private val _missionId = MutableLiveData<Int>()
    val missionId = _missionId

    init {
        setMissionRecord()
    }

    private fun setMissionRecord() {
        viewModelScope.launch {
            shortGameRepository.getShortGameResult(roundGameId).onSuccess {
                myMissionResult.value = it.myRoundMission
                missionId.value = it.myRoundMission.missionContent.missionCategory.id
            }.onFailure {
                // TODO: 실패 로직 구현
            }
        }
    }

    fun requestMission(missionRequest: Boolean) {
        viewModelScope.launch {
            shortGameRepository.recordShortGame(roundGameId, missionRequest).onSuccess {
                _isMissionRequestSuccess.postValue(true)
            }.onFailure {
                // TODO: 실패 로직 구현
                Timber.tag("testt2").d(it.toString())
            }
        }
    }

    fun requestStopMission() {
        viewModelScope.launch {
            shortGameRepository.deleteShortGame(roundGameId).onSuccess {
                _isMissionDeleteSuccess.postValue(true)
            }.onFailure {
                // TODO: 실패 로직 구현
                Timber.tag("testt3").d(it.toString())
            }
        }
    }
}
