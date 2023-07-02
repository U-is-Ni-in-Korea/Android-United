package sopt.uni_aos.data.repository.example

import sopt.uni_aos.data.entity.ExampleEntity
import sopt.uni_aos.data.source.remote.request.ExampleRequestDto

class ExampleRepositoryImpl() : ExampleRepository {
    override suspend fun postExample(exampleRequestDto: ExampleRequestDto): Result<ExampleEntity> =
        Result.success(ExampleEntity(1))
}