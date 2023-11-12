package sopt.uni.presentation.shortgame.missionresult

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
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

    init {
        setMissionRecord()
        Log.e("subin", "result viewModel init")
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

            shortGameRepository.getShortGameFinalResult(roundGameId).onSuccess {
             //   Log.e("subin", "getShortGameFinalResult - onSuccess 실행함")
                if (it.partnerRoundMission != null) {
                    _partnerMissionResult.value =
                        it.partnerRoundMission
                }
            //    Log.e("subin", "_partnerMissionResult.value: ${_partnerMissionResult.value}")
            }.onFailure {
                Log.e("subin", "getShortGameFinalResult - onFailure 실행함")
                // TODO: 실패 로직 구현
            }
        }
    }
}
