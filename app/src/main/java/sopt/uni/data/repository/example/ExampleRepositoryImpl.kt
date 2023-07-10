package sopt.uni.data.repository.example

import sopt.uni.data.entity.onboarding.OnBoardingItem
import sopt.uni.data.source.remote.request.ExampleRequestDto

class ExampleRepositoryImpl() : ExampleRepository {
    override suspend fun postExample(exampleRequestDto: ExampleRequestDto): Result<OnBoardingItem> =
        Result.success(OnBoardingItem(0,"",""))
}
