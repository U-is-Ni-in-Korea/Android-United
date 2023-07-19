package sopt.uni.data.service

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.Part
import sopt.uni.data.source.remote.response.MyPageInfoResponseDto
import sopt.uni.data.source.remote.response.MyPageResponseDto

interface MyPageService {
    @GET("api/user")
    suspend fun getMyPageInfo(): MyPageResponseDto

    @Multipart
    @PATCH("api/user/info")
    suspend fun sendMyPageInfo(
        @Part image: MultipartBody.Part?,
        @Part("nickname") nickname: RequestBody,
        @Part("startDate") startDate: RequestBody,
    ): MyPageInfoResponseDto
}
