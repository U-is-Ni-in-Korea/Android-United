package sopt.uni.data.service

import retrofit2.http.GET
import sopt.uni.data.source.remote.response.HistoryResponseDto

interface HistoryService {
    @GET("api/history")
    suspend fun getHistoryList(): List<HistoryResponseDto>
}
