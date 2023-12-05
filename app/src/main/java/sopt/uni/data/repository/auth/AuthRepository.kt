package sopt.uni.data.repository.auth

import sopt.uni.data.source.remote.response.ResponseAuthDto

interface AuthRepository {
    suspend fun getToken(social: String, code: String): Result<ResponseAuthDto>

    suspend fun initToken(accessToken: String, refreshToken: String)

    suspend fun clearToken()
}
