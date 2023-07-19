package sopt.uni.data.service

import retrofit2.http.GET
import sopt.uni.data.entity.shortgame.MissionDetail

interface ShortGameService {
    @GET("/api/mission")
    suspend fun getMissionCategoryList(): List<MissionDetail>
}
