package sopt.uni.data.repository.onboarding

import sopt.uni.data.service.OnBoardingService
import sopt.uni.data.source.remote.request.RequestNickNameDto
import javax.inject.Inject

class OnBoardingRepositoryImpl @Inject constructor(
    private val onBoardingService: OnBoardingService,
) : OnBoardingRepository {

    override suspend fun patchNickName(nickname: String) {
        onBoardingService.patchNickName(RequestNickNameDto(nickname))
    }
}