package sopt.uni.data.service

import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path
import sopt.uni.data.source.remote.request.RequestAuthDto
import sopt.uni.data.source.remote.response.ResponseAuthDto

interface AuthService {

    @POST("auth/{social}")
    suspend fun getToken(
        @Path("social") social: String,
        @Body request: RequestAuthDto,
    ): ResponseAuthDto
}
