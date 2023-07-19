package sopt.uni.data.repository.shortgame

import sopt.uni.data.entity.shortgame.MissionDetail
import sopt.uni.data.source.remote.response.ResponseCreateShortGameDto

interface ShortGameRepository {
    suspend fun getMissionCategory(): Result<List<MissionDetail>>

    suspend fun createShortGame(
        missionCategoryId: Int,
        wishContent: String,
    ): Result<ResponseCreateShortGameDto>
}
