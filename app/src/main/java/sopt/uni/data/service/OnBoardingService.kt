package sopt.uni.data.service

import retrofit2.http.Body
import retrofit2.http.PATCH
import retrofit2.http.POST
import sopt.uni.data.source.remote.request.RequestNickNameDto
import sopt.uni.data.source.remote.request.RequestStartDateDto
import sopt.uni.data.source.remote.response.ResponseNickNameDto
import sopt.uni.data.source.remote.response.ResponseStartDateDto

interface OnBoardingService {

    @PATCH("api/user")
    suspend fun patchNickName(
        @Body request: RequestNickNameDto,
    ): ResponseNickNameDto

    @POST("api/couple")
    suspend fun postStartDate(
        @Body request: RequestStartDateDto,
    ): ResponseStartDateDto
}
