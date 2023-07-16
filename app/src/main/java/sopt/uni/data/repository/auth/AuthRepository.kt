package sopt.uni.data.repository.auth

import sopt.uni.data.entity.auth.Token

interface AuthRepository {
    suspend fun getToken(social: String, code: String): Result<Token>

    suspend fun initToken(accessToken: String, refreshToken: String)

    suspend fun clearToken()
}
