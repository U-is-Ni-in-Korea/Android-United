package sopt.uni.data.service

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import sopt.uni.data.source.remote.request.RequestAuthDto
import sopt.uni.data.source.remote.response.ResponseAuthDto

interface AuthService {

    @GET("auth/{social}")
    suspend fun getToken(
        @Path("social") social: String,
        @Query("code") request: RequestAuthDto,
    ): ResponseAuthDto
}
