package sopt.uni.presentation.shortgame.missionrecord

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import sopt.uni.data.entity.shortgame.MissionToolState
import sopt.uni.data.entity.shortgame.RoundMission
import sopt.uni.data.repository.shortgame.ShortGameRepository
import javax.inject.Inject

@HiltViewModel
class MissionRecordViewModel @Inject constructor(
    private val shortGameRepository: ShortGameRepository,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    val roundGameId: Int = savedStateHandle.get<Int>(MissionRecordActivity.ROUND_GAME_ID) ?: -1

    private val _isMissionRequestSuccess = MutableLiveData<Boolean>(false)
    val isMissionRequestSuccess = _isMissionRequestSuccess

    private val _myMissionResult = MutableLiveData<RoundMission>()
    val myMissionResult = _myMissionResult

    private val _missionId = MutableLiveData<Int>()
    val missionId = _missionId

    private val _partnerMissionResult = MutableLiveData<RoundMission?>()
    val partnerMissionResult = _partnerMissionResult

    private val _partnerId = MutableLiveData<Int?>()
    val partnerId = _partnerId

    private val _missionToolState = MutableLiveData<MissionToolState>()
    val missionToolState = _missionToolState

    init {
        setMissionRecord()
    }

    private fun setMissionRecord() {
        viewModelScope.launch {
            shortGameRepository.getShortGameResult(roundGameId).onSuccess {
                _myMissionResult.value = it.myRoundMission
                _missionId.value = it.myRoundMission.missionContent.missionCategory.id
                _missionToolState.value =
                    MissionToolState.getMissionTool(it.myRoundMission.missionContent.missionCategory.missionTool)
            }.onFailure {
                // TODO: 실패 로직 구현
            }
        }
    }

    fun requestMission(missionRequest: Boolean) {
        viewModelScope.launch {
            shortGameRepository.recordShortGame(roundGameId, missionRequest).onSuccess {
                _isMissionRequestSuccess.postValue(true)
                resetMemoText()
            }.onFailure {
                // TODO: 실패 로직 구현
            }
        }
    }

    private fun resetMemoText() {
        shortGameRepository.setMemoText("")
    fun getMissionResult(){
        Log.e("subin","get function start")
        viewModelScope.launch{
            shortGameRepository.getShortGameFinalResult(roundGameId).onSuccess {
                _partnerMissionResult.postValue(it.partnerRoundMission)
                Log.e("subin","${it.partnerRoundMission.toString()} server data value")
                Log.e("subin","${_partnerMissionResult.value.toString()} livedata value")
                _isMissionRequestSuccess.postValue(true)
            }.onFailure {
                Log.e("subin",it.toString())
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
