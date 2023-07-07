package sopt.uni.data.repository.example

import sopt.uni.data.entity.ExampleEntity
import sopt.uni.data.source.remote.request.ExampleRequestDto

interface ExampleRepository {
    suspend fun postExample(exampleRequestDto: ExampleRequestDto): Result<ExampleEntity>
}
