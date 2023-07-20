package sopt.uni.data.repository.home

import sopt.uni.data.source.remote.response.ResponseHomeDto

interface HomeRepository {
    suspend fun getHomeInfo(): Result<ResponseHomeDto>
}
