package sopt.uni.presentation.shortgame.createshortgame

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import sopt.uni.data.entity.shortgame.MissionDetail
import sopt.uni.data.repository.shortgame.ShortGameRepository
import javax.inject.Inject

@HiltViewModel
class CreateShortGameViewModel @Inject constructor(private val shortGameRepository: ShortGameRepository) :
    ViewModel() {
    private val _selectedMissionId = MutableLiveData<Int>()
    val selectedMissionId = _selectedMissionId

    private val _roundGameId = MutableLiveData<Int>()
    val roundGameId = _roundGameId

    private val _missionList = MutableLiveData<List<MissionDetail>>()
    val missionList = _missionList

    val wishContent = MutableLiveData<String>("")

    val contentLength = wishContent.map {
        it.length
    }

    private val _isCreateSuccess = MutableLiveData<Boolean>(false)
    val isCreateSuccess = _isCreateSuccess

    init {
        getMissionCategory()
    }

    private fun getMissionCategory() {
        viewModelScope.launch {
            shortGameRepository.getMissionCategory().onSuccess {
                _missionList.postValue(it)
                selectedMissionId.postValue(it[0].id)
            }.onFailure {
                // TODO: 실패시 예외처리
            }
        }
    }

    fun createShortGame() {
        viewModelScope.launch {
            shortGameRepository.createShortGame(
                selectedMissionId.value ?: return@launch,
                wishContent.value ?: "",
            ).onSuccess {
                _roundGameId.value = it.roundGameId
                _isCreateSuccess.value = true
            }.onFailure {
                // TODO: 실패시 예외처리
            }
        }
    }

    fun setSelectedMissionId(missionId: Int) {
        _selectedMissionId.value = missionId
    }
}
