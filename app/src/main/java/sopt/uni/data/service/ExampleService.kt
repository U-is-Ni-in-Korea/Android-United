package sopt.uni.data.service

import retrofit2.http.Body
import retrofit2.http.POST
import sopt.uni.data.source.remote.request.ExampleRequestDto
import sopt.uni.data.source.remote.response.ExampleResponseDto

interface ExampleService {
    @POST("example/api")
    suspend fun postExample(
        @Body request: ExampleRequestDto,
    ): ExampleResponseDto
}
