package sopt.uni.data.repository.history

import sopt.uni.data.service.HistoryService
import sopt.uni.data.source.remote.response.HistoryResponse

class HistoryRepositoryImpl(
    private val historyService: HistoryService,
) : HistoryRepository {
    override suspend fun getHistoryList(): Result<HistoryResponse> {
        val token = ""
        return try {
            val historyResponse = historyService.getHistoryList("Bearer $token")
            Result.success(historyResponse)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
