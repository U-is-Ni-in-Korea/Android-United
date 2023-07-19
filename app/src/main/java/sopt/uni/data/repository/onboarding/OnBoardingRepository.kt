package sopt.uni.data.repository.onboarding

interface OnBoardingRepository {

    suspend fun patchNickName(nickname: String)

    suspend fun postStartDate(startDate: String): String

    suspend fun checkCoupleConnection(): Boolean
}
