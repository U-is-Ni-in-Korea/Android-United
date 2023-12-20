package sopt.uni.data.repository.onboarding

import retrofit2.Response
import sopt.uni.data.source.remote.response.ResponseStartDateDto

interface OnBoardingRepository {

    suspend fun patchNickName(nickname: String)

    suspend fun postStartDate(startDate: String): Response<ResponseStartDateDto>

    suspend fun checkCoupleConnection(): Boolean

    suspend fun postCheckConnection(inviteCode: String): Response<Unit>
}
