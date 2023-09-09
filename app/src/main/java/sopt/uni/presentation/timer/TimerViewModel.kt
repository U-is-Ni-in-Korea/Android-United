package sopt.uni.presentation.timer

import android.util.Log
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


    private var _leftTime = MutableLiveData<Long>()
    val leftTime: LiveData<Long>
        get() = _leftTime

    fun updateLeftTime(secondsRemaining: Long) {
        _leftTime.value = secondsRemaining
        // LiveData 값 로그로 출력
        Log.d("TimerViewModel", "updateLeftTime: $secondsRemaining")
    }

    fun setMaxTime(maxTime: Float) {
        _maxTime.value = maxTime
    }
}
