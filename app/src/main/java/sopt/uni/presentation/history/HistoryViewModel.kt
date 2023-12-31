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
import sopt.uni.util.UiState
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val historyRepository: HistoryRepository,
) : ViewModel() {
    private var _historyData = MutableLiveData<UiState<List<History>>>(UiState.Loading)
    val historyData: LiveData<UiState<List<History>>> = _historyData

    init {
        getHistoryList()
    }

    private fun getHistoryList() {
        viewModelScope.launch {
            runCatching {
                val result = historyRepository.getHistoryList()
                result.getOrElse { emptyList() }
            }.fold(
                onSuccess = { historyList ->
                    if (historyList.isEmpty()) {
                        _historyData.value = UiState.Empty
                    } else {
                        _historyData.value = UiState.Success(historyList)
                    }
                },
                onFailure = { throwable ->
                    _historyData.value = throwable.message?.let { UiState.Failure(it) }
                },
            )
        }
    }
}
