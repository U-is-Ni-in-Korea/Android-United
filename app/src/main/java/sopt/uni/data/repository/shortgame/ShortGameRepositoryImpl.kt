package sopt.uni.data.repository.shortgame

import sopt.uni.data.datasource.local.LocalPreferenceDataSource
import sopt.uni.data.entity.shortgame.MissionDetail
import sopt.uni.data.service.ShortGameService
import sopt.uni.data.source.remote.request.RequestCreateShortGameDto
import sopt.uni.data.source.remote.request.RequestRecordShortGameDto
import sopt.uni.data.source.remote.response.ResponseCreateShortGameDto
import sopt.uni.data.source.remote.response.ResponseShortGameResultDto
import javax.inject.Inject

class ShortGameRepositoryImpl @Inject constructor(
    private val shortGameService: ShortGameService,
    private val memoDataSource: LocalPreferenceDataSource,
) : ShortGameRepository {
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

    override suspend fun getMissionDetail(missionCategoryId: Int): Result<MissionDetail> =
        kotlin.runCatching {
            shortGameService.getMissionDetail(missionCategoryId)
        }.onSuccess {
            Result.success(it)
        }.onFailure {
            Result.failure<MissionDetail>(it)
        }

    override suspend fun getShortGameResult(
        roundGameId: Int,
    ): Result<ResponseShortGameResultDto> =
        kotlin.runCatching {
            shortGameService.getShortGameResult(roundGameId)
        }.onSuccess {
            Result.success(it)
        }.onFailure {
            Result.failure<ResponseShortGameResultDto>(it)
        }

    override suspend fun recordShortGame(
        roundGameId: Int,
        result: Boolean,
    ): Result<ResponseShortGameResultDto> =
        kotlin.runCatching {
            val requestBody = RequestRecordShortGameDto(result)
            shortGameService.patchShortGameResult(roundGameId, requestBody)
        }.onSuccess {
            Result.success(it)
        }.onFailure {
            Result.failure<ResponseShortGameResultDto>(it)
        }

    override suspend fun deleteShortGame(roundGameId: Int): Result<Unit> =
        kotlin.runCatching {
            shortGameService.deleteShortGameResult(roundGameId).body() ?: Unit
        }.onSuccess {
            Result.success(Unit)
        }.onFailure {
            Result.failure<Unit>(it)
        }

    override fun getMemoText(): String {
        return memoDataSource.getMemo()
    }

    override fun setMemoText(text: String) {
        memoDataSource.setMemo(text)
    }
}
