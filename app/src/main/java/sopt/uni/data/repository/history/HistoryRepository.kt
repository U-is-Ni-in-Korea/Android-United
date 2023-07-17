package sopt.uni.data.repository.history

import sopt.uni.data.source.remote.response.HistoryResponse

interface HistoryRepository {

    suspend fun getHistoryList(): Result<HistoryResponse>
}
