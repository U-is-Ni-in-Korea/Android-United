package sopt.uni.data.repository.example

import sopt.uni.data.entity.ExampleEntity
import sopt.uni.data.source.remote.request.ExampleRequestDto

class ExampleRepositoryImpl() : ExampleRepository {
    override suspend fun postExample(exampleRequestDto: ExampleRequestDto): Result<ExampleEntity> =
        Result.success(ExampleEntity(1))
}