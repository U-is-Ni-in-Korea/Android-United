package sopt.uni.data.repository.onboarding

interface OnBoardingRepository {

    suspend fun patchNickName(nickname: String)
}
