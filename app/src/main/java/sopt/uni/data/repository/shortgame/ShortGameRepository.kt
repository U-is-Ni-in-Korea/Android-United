package sopt.uni.data.repository.shortgame

import sopt.uni.data.entity.shortgame.MissionDetail
import sopt.uni.data.source.remote.response.ResponseCreateShortGameDto
import sopt.uni.data.source.remote.response.ResponseShortGameResultDto

interface ShortGameRepository {
    suspend fun getMissionCategory(): Result<List<MissionDetail>>

    suspend fun createShortGame(
        missionCategoryId: Int,
        wishContent: String,
    ): Result<ResponseCreateShortGameDto>

    suspend fun getMissionDetail(missionCategoryId: Int): Result<MissionDetail>

    suspend fun getShortGameResult(roundGameId: Int): Result<ResponseShortGameResultDto>

    suspend fun getShortGameFinalResult(roundGameId: Int): Result<ResponseShortGameResultDto>

    suspend fun recordShortGame(
        roundGameId: Int,
        result: Boolean,
    ): Result<ResponseShortGameResultDto>

    suspend fun deleteShortGame(roundGameId: Int): Result<Unit>

    fun getMemoText(): String

    fun setMemoText(text: String): Unit
}
