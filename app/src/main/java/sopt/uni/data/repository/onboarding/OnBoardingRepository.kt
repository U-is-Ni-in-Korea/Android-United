package sopt.uni.data.repository.onboarding

import retrofit2.Response

interface OnBoardingRepository {

    suspend fun patchNickName(nickname: String)

    suspend fun postStartDate(startDate: String): String

    suspend fun checkCoupleConnection(): Boolean

    suspend fun postCheckConnection(inviteCode: String): Response<Unit>
}
