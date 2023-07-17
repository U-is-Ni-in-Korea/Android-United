package sopt.uni.data.service

import retrofit2.http.Body
import retrofit2.http.PATCH
import sopt.uni.data.source.remote.request.RequestNickNameDto
import sopt.uni.data.source.remote.response.ResPonseNickNameDto

interface OnBoardingService {

    @PATCH
    suspend fun patchNickName(
        @Body request: RequestNickNameDto,
    ): ResPonseNickNameDto
}
