package sopt.uni.data.service

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import sopt.uni.data.entity.shortgame.MissionDetail
import sopt.uni.data.source.remote.request.RequestCreateShortGameDto
import sopt.uni.data.source.remote.response.ResponseCreateShortGameDto

interface ShortGameService {
    @GET("/api/mission")
    suspend fun getMissionCategoryList(): List<MissionDetail>

    @POST("/api/game/short")
    suspend fun postCreateShortGame(
        @Body request: RequestCreateShortGameDto,
    ): ResponseCreateShortGameDto
}
