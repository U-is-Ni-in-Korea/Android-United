package sopt.uni.presentation.shortgame.missionresult

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import sopt.uni.data.entity.shortgame.MissionResultState
import sopt.uni.data.source.remote.response.ResponseShortGameResultDto
import sopt.uni.presentation.shortgame.missionrecord.MissionRecordActivity

class MissionResultViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    // TODO: response 네이밍 바꾸고 Result로 감싸서 성공 실패 분기 처리하기
    val roundGameId: Int = savedStateHandle.get<Int>(MissionRecordActivity.ROUND_GAME_ID) ?: -1
    private val _missionResult = MutableLiveData<ResponseShortGameResultDto>()
    val missionResult: LiveData<ResponseShortGameResultDto> = _missionResult
    val myMissionResult = missionResult.map { it.myRoundMission }
    val partnerMissionResult = missionResult.map { it.partnerRoundMission }
    val myMissionResultState =
        myMissionResult.map { MissionResultState.getMissionResultType(it.finalResult) }
}
