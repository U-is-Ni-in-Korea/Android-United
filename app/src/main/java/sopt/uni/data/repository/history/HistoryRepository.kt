package sopt.uni.data.repository.history

import sopt.uni.data.entity.history.History

interface HistoryRepository {
    suspend fun getHistoryList(): Result<List<History>>
}
