package sopt.uni.data.service

import retrofit2.http.GET
import sopt.uni.data.source.remote.response.ResponseHomeDto

interface HomeService {

    @GET("api/home")
    suspend fun getHome(): ResponseHomeDto
}
