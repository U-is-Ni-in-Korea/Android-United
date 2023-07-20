package sopt.uni.data.repository.home

import sopt.uni.data.service.HomeService
import sopt.uni.data.source.remote.response.ResponseHomeDto
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeService: HomeService,
) : HomeRepository {
    override suspend fun getHomeInfo(): Result<ResponseHomeDto> =
        kotlin.runCatching {
            homeService.getHome()
        }.fold({
            Result.success(it)
        }, {
            Result.failure(it)
        })
}
