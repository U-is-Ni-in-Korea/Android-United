package sopt.uni.data.service

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import sopt.uni.data.entity.shortgame.MissionDetail
import sopt.uni.data.source.remote.request.RequestCreateShortGameDto
import sopt.uni.data.source.remote.request.RequestRecordShortGameDto
import sopt.uni.data.source.remote.response.ResponseCreateShortGameDto
import sopt.uni.data.source.remote.response.ResponseShortGameResultDto

interface ShortGameService {
    @GET("/api/mission")
    suspend fun getMissionCategoryList(): List<MissionDetail>

    @POST("/api/game/short")
    suspend fun postCreateShortGame(
        @Body request: RequestCreateShortGameDto,
    ): ResponseCreateShortGameDto

    @GET("/api/mission/{missionCategoryId}")
    suspend fun getMissionDetail(
        @Path("missionCategoryId") missionCategoryId: Int,
    ): MissionDetail

    @GET("/api/game/short/{roundGameId}")
    suspend fun getShortGameResult(
        @Path("roundGameId") roundGameId: Int,
    ): ResponseShortGameResultDto

    @PATCH("/api/game/short/{roundGameId}")
    suspend fun patchShortGameResult(
        @Path("roundGameId") roundGameId: Int,
        @Body request: RequestRecordShortGameDto,
    ): ResponseShortGameResultDto
}
