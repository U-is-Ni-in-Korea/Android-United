// HistoryViewModel.kt
package sopt.uni.presentation.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import sopt.uni.data.entity.history.History
import sopt.uni.data.repository.history.HistoryRepository
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val historyRepository: HistoryRepository,
) : ViewModel() {
    private val _historyData = MutableLiveData<List<History>>(emptyList())
    val historyData: LiveData<List<History>> = _historyData

    init {
        getHistoryList()
    }

    private fun getHistoryList() {
        viewModelScope.launch {
            kotlin.runCatching {
                val result = historyRepository.getHistoryList()
                result.getOrNull()
            }.fold(
                onSuccess = { historyList ->
                    _historyData.value = historyList
                },
                onFailure = {
                },
            )
        }
    }
}
