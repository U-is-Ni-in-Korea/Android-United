package sopt.uni.data.repository.shortgame

import sopt.uni.data.entity.auth.Token
import sopt.uni.data.entity.shortgame.MissionDetail
import sopt.uni.data.service.ShortGameService
import javax.inject.Inject

class ShortGameRepositoryImpl @Inject constructor(private val shortGameService: ShortGameService) :
    ShortGameRepository {
    override suspend fun getMissionCategory(): Result<List<MissionDetail>> =
        kotlin.runCatching {
            shortGameService.getMissionCategoryList()
        }.onSuccess {
            Result.success(it)
        }.onFailure {
            Result.failure<Token>(it)
        }
}
