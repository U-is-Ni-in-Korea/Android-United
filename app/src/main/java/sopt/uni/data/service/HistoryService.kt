package sopt.uni.data.service

import retrofit2.http.GET
import retrofit2.http.Header
import sopt.uni.data.source.remote.response.HistoryResponse

interface HistoryService {
    @GET("/api/history")
    suspend fun getHistoryList(
        @Header("Authorization") token: String,
    ): HistoryResponse
}
