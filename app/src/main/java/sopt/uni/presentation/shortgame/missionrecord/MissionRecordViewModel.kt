package sopt.uni.presentation.shortgame.missionrecord

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import sopt.uni.data.source.remote.response.ResponseShortGameResultDto

class MissionRecordViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    val roundGameId: Int = savedStateHandle.get<Int>(MissionRecordActivity.ROUND_GAME_ID) ?: -1

    private val _missionResult = MutableLiveData<ResponseShortGameResultDto>()
    val missionResult: LiveData<ResponseShortGameResultDto> = _missionResult

    private val _isMissionCancelSuccess = MutableLiveData<Boolean>(false)
    val isMissionCancelSuccess = _isMissionCancelSuccess

    private val _isMissionRequestSuccess = MutableLiveData<Boolean>(false)
    val isMissionRequestSuccess = _isMissionRequestSuccess

    val myMissionResult = missionResult.map { it.myRoundMission }
    val missionId = myMissionResult.map { it.missionContent.id }

    fun loadMissionRecord() {
        // TODO: Get 승부기록뷰 정보
    }

    fun requestMission(missionRequest: Boolean) {
        _isMissionRequestSuccess.postValue(true)
    }

    fun requestStopMission() {
        _isMissionCancelSuccess.postValue(true)
    }
}
