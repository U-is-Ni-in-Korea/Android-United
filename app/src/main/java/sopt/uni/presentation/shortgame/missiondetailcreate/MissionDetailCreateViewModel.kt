package sopt.uni.presentation.shortgame.missiondetailcreate

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import sopt.uni.data.entity.shortgame.MissionDetail
import sopt.uni.data.repository.shortgame.ShortGameRepository
import sopt.uni.presentation.entity.MissionIdPosition
import javax.inject.Inject

@HiltViewModel
class MissionDetailCreateViewModel @Inject constructor(
    private val shortGameRepository: ShortGameRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    val missionId =
        savedStateHandle.get<MissionIdPosition>(MissionDetailCreateActivity.MISSION_ID_POSITION)?.id

    private val _missionDetail = MutableLiveData<MissionDetail>()
    val missionDetail = _missionDetail

    init {
        setMissionDetail()
    }

    private fun setMissionDetail() {
        viewModelScope.launch {
            shortGameRepository.getMissionDetail(missionId ?: return@launch).onSuccess {
                _missionDetail.postValue(it)
            }.onFailure {
                // TODO: 실패로직 구현
            }
        }
    }
}
