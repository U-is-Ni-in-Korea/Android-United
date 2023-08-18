// HistoryViewModel.kt
package sopt.uni.presentation.history

import android.util.Log
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
            kotlin.runCatching {
                val result = historyRepository.getHistoryList()
                result.getOrElse { emptyList() }
            }.fold(
                onSuccess = { historyList ->
                    _historyData.value = UiState.Success(historyList)
                    Log.d("aaaa", "성공")
                },
                onFailure = { throwable ->
                    _historyData.value = throwable.message?.let { UiState.Failure(it) }
                    Log.d("aaaa", "실패")
                },
            )
        }
    }
}
