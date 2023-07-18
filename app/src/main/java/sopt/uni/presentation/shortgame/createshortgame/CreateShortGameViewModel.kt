package sopt.uni.presentation.shortgame.createshortgame

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import sopt.uni.data.entity.shortgame.Mission

class CreateShortGameViewModel : ViewModel() {
    private val _selectedMissionId = MutableLiveData<Int>()
    val selectedMissionId = _selectedMissionId

    private val _roundGameId = MutableLiveData<Int>()
    val roundGameId = _roundGameId

    private val _missionList = MutableLiveData<List<Mission>>()
    val missionList = _missionList

    val wishContent = MutableLiveData<String>("")

    val contentLength = wishContent.map {
        it.length
    }

    private val _isCreateSuccess = MutableLiveData<Boolean>(false)
    val isCreateSuccess = _isCreateSuccess

    init {
        setDummyList()
    }

    private fun setDummyList() {
        val missionList = mutableListOf<Mission>()

        for (i in 0..7) {
            missionList.add(
                Mission(
                    image = "https://github.com/U-is-Ni-in-Korea/Android-United/assets/50603273/8c345eb3-d688-42bd-8585-a02f1016e213",
                    title = "뀨$i",
                    id = i,
                ),
            )
        }
        _missionList.value = missionList
    }

    fun createShortGame() {
        _isCreateSuccess.postValue(true)
        _roundGameId.value = 1
    }

    fun setSelectedMissionId(missionId: Int) {
        _selectedMissionId.value = missionId
    }
}
