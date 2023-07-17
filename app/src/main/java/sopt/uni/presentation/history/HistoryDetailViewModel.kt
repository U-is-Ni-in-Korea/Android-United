package sopt.uni.presentation.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import sopt.uni.data.entity.history.History

/** TODO 준희 : @HiltViewModel 붙이기*/
class HistoryDetailViewModel : ViewModel() {
    private val _history = MutableLiveData<History>()
    val history: LiveData<History> = _history

    fun setHistory(history: History) {
        _history.value = history
    }
}
