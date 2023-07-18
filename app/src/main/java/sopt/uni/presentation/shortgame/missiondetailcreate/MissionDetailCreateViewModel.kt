package sopt.uni.presentation.shortgame.missiondetailcreate

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import sopt.uni.data.entity.shortgame.MissionDetail
import sopt.uni.data.entity.shortgame.MissionExample
import sopt.uni.presentation.entity.MissionIdPosition

class MissionDetailCreateViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    val missionId = savedStateHandle.get<MissionIdPosition>(MissionDetailCreateActivity.MISSION_ID_POSITION)

    private val _missionDetail = MutableLiveData<MissionDetail>()
    val missionDetail = _missionDetail

    init {
        setMissionDetail()
    }

    fun setMissionDetail() {
        _missionDetail.value = MissionDetail(
            1,
            "금지어 말하지 않기",
            "금지이름 말하지 않는 게임입니다",
            "본인에게 할당된 주제의 답변을 상대방이 맞춰야 하는 게임입니다.\n" + "단순 주제가 나와있는 미션이라면, 상대의 입장에서 해당 주제에 대한 답을 해주세요.\n" + "2지선다로 이루어진 미션이라면, 상대가 어떤 선지를 더 좋아할지에 대해 예상하여 답을 해주세요.",
            "요리조리 피해보세요",
            "https://github.com/U-is-Ni-in-Korea/Android-United/assets/50603273/8c345eb3-d688-42bd-8585-a02f1016e213",
            listOf(MissionExample(1, "헐"), MissionExample(2, "대박")),
        )
    }
}
