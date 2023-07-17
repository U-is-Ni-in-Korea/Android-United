package sopt.uni.data.repository.history

import sopt.uni.data.service.HistoryService
import sopt.uni.data.source.remote.response.HistoryResponse

class HistoryRepositoryImpl(
    private val historyService: HistoryService,
) : HistoryRepository {
    override suspend fun getHistoryList(): Result<HistoryResponse> {
        val token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJhY2Nlc3NUb2tlbiIsImlhdCI6MTY4OTQ0ODI5MywiZXhwIjoxNjkxMjQ4MjkzLCJ1c2VySWQiOjV9.05nNO-UkGtlg2iCY22039x-TTaSZ0NLxkqHbqqnIpbYRiiJbW0nH-Ckt_x-tbRF1"
        return try {
            val historyResponse = historyService.getHistoryList("Bearer $token")
            Result.success(historyResponse)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
