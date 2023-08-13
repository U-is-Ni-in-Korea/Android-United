package sopt.uni.presentation.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import sopt.uni.data.entity.history.History
import javax.inject.Inject

@HiltViewModel
class HistoryDetailViewModel @Inject constructor() : ViewModel() {
    private var _history = MutableLiveData<History>()
    val history: LiveData<History> = _history

    fun setHistory(history: History) {
        _history.value = history
    }
}
