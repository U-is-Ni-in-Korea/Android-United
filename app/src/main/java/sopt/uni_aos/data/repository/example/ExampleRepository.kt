package sopt.uni_aos.data.repository.example

import sopt.uni_aos.data.entity.ExampleEntity
import sopt.uni_aos.data.source.remote.request.ExampleRequestDto

interface ExampleRepository {
    suspend fun postExample(exampleRequestDto: ExampleRequestDto): Result<ExampleEntity>
}
