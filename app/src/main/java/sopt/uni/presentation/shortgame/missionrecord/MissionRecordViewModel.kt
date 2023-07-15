package sopt.uni.presentation.shortgame.missionrecord

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class MissionRecordViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    val roundGameId: Int = savedStateHandle.get<Int>(MissionRecordActivity.ROUND_GAME_ID) ?: -1
    val missionId = MutableLiveData<Int>(1)
    val missionImage = MutableLiveData<String>("")
    val missionContent = MutableLiveData<String>("헤드셋")
    val missionTitle = MutableLiveData<String>("키워드 스무고개")

    fun loadMissionRecord() {
        // TODO: Get 승부기록뷰 정보
    }

    fun requestMission(missionRequest: Boolean) {
        // TODO: 미션완료,실패 API 호출
    }

    fun requestStopMission() {
        // TODO: 승부그만두기 API 호출
    }
}
