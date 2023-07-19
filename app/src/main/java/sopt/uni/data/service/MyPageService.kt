package sopt.uni.data.service

import retrofit2.http.GET
import sopt.uni.data.source.remote.response.MyPageResponseDto

interface MyPageService {
    @GET("api/user")
    suspend fun getMyPageInfo(): MyPageResponseDto
}
