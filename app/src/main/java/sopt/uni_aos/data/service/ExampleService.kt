package sopt.uni_aos.data.service

import retrofit2.http.Body
import retrofit2.http.POST
import sopt.uni_aos.data.source.remote.request.ExampleRequestDto
import sopt.uni_aos.data.source.remote.response.ExampleResponseDto

interface ExampleService {
    @POST("example/api")
    suspend fun postExample(
        @Body request: ExampleRequestDto,
    ): ExampleResponseDto
}
