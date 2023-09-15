package sopt.uni.presentation.timer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TimerViewModel @Inject constructor() : ViewModel() {

    private var _maxTime = MutableLiveData<Float>()
    val maxTime: LiveData<Float>
        get() = _maxTime

    private var _leftTime = MutableLiveData<Long?>()
    val leftTime: LiveData<Long?>
        get() = _leftTime

    private var _snackbarMessage = MutableLiveData<String?>()
    val snackbarMessage: LiveData<String?>
        get() = _snackbarMessage

    fun updateLeftTime(secondsRemaining: Long?) {
        _leftTime.value = secondsRemaining
    }

    fun setMaxTime(maxTime: Float) {
        _maxTime.value = maxTime
    }

    fun setSnackbarMessage(message: String) {
        _snackbarMessage.value = message
    }

    fun resetSnackbarMessage() {
        _snackbarMessage.value = null
    }
}
