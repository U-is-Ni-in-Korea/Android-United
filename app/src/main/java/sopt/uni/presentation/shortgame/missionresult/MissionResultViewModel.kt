package sopt.uni.presentation.shortgame.missionresult

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import sopt.uni.data.entity.shortgame.MissionResultState
import sopt.uni.data.entity.shortgame.RoundMission
import sopt.uni.data.repository.shortgame.ShortGameRepository
import sopt.uni.presentation.shortgame.missionrecord.MissionRecordActivity
import javax.inject.Inject

@HiltViewModel
class MissionResultViewModel @Inject constructor(
    private val shortGameRepository: ShortGameRepository,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val roundGameId: Int = savedStateHandle.get<Int>(MissionRecordActivity.ROUND_GAME_ID) ?: -1

    private val _myMissionResult = MutableLiveData<RoundMission>()
    val myMissionResult = _myMissionResult

    private val _partnerMissionResult = MutableLiveData<RoundMission?>()
    val partnerMissionResult = _partnerMissionResult

    private val _myMissionResultState = MutableLiveData<MissionResultState>()
    val myMissionResultState = _myMissionResultState

    private val _isMissionFinalRequestSuccess = MutableLiveData<Boolean>(false)
    val isMissionFinalRequestSuccess = _isMissionFinalRequestSuccess

    init {
        setMissionRecord()
        setFinalResultRecord()
    }

    private fun setMissionRecord() {
        viewModelScope.launch {
            shortGameRepository.getShortGameResult(roundGameId).onSuccess {
                _myMissionResult.value = it.myRoundMission
                _myMissionResultState.value =
                    MissionResultState.getMissionResultType(it.myRoundMission.finalResult)
            }.onFailure {
                // TODO: 실패 로직 구현
            }
        }
    }

    private fun setFinalResultRecord(): Job {
        return viewModelScope.launch {
            shortGameRepository.getShortGameFinalResult(roundGameId).onSuccess {
                if (it.partnerRoundMission != null) {
                    _partnerMissionResult.value =
                        it.partnerRoundMission
                }
                _isMissionFinalRequestSuccess.value = true
            }.onFailure {
                _isMissionFinalRequestSuccess.value = false
            }
        }
    }

    fun getFinalRequestResult(): Job {
        return viewModelScope.launch {
            setFinalResultRecord().join()
        }
    }
}
