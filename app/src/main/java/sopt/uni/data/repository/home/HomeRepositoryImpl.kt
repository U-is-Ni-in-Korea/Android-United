package sopt.uni.data.repository.home

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import sopt.uni.data.service.HomeService
import sopt.uni.data.source.remote.response.ErrorCode
import sopt.uni.data.source.remote.response.ResponseHomeDto
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeService: HomeService,
) : HomeRepository {
    override suspend fun getHomeInfo(): Result<ResponseHomeDto> =
        kotlin.runCatching {
            val response = homeService.getHome()
            if (response.isSuccessful) {
                response.body() ?: throw Exception("Response body is null")
            } else {
                val errorResponse =
                    Json.decodeFromString<ErrorCode>(response.errorBody()?.string().orEmpty())
                throw Exception(errorResponse.code)
            }
        }
}
