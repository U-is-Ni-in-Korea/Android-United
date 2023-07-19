package sopt.uni.data.repository.shortgame

import sopt.uni.data.entity.shortgame.MissionDetail
import sopt.uni.data.service.ShortGameService
import sopt.uni.data.source.remote.request.RequestCreateShortGameDto
import sopt.uni.data.source.remote.response.ResponseCreateShortGameDto
import javax.inject.Inject

class ShortGameRepositoryImpl @Inject constructor(private val shortGameService: ShortGameService) :
    ShortGameRepository {
    override suspend fun getMissionCategory(): Result<List<MissionDetail>> =
        kotlin.runCatching {
            shortGameService.getMissionCategoryList()
        }.onSuccess {
            Result.success(it)
        }.onFailure {
            Result.failure<List<MissionDetail>>(it)
        }

    override suspend fun createShortGame(
        missionCategoryId: Int,
        wishContent: String,
    ): Result<ResponseCreateShortGameDto> =
        kotlin.runCatching {
            val requestBody = RequestCreateShortGameDto(missionCategoryId, wishContent)
            shortGameService.postCreateShortGame(requestBody)
        }.onSuccess {
            Result.success(it)
        }.onFailure {
            Result.failure<ResponseCreateShortGameDto>(it)
        }
}
