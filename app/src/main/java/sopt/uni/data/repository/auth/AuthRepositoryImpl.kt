package sopt.uni.data.repository.auth

import sopt.uni.data.datasource.remote.AuthDatasource
import sopt.uni.data.entity.auth.Token
import sopt.uni.data.source.remote.request.RequestAuthDto
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDatasource: AuthDatasource,
) : AuthRepository {
    override suspend fun getToken(social: String, code: String): Result<Token> =
        kotlin.runCatching {
            authDatasource.getToken(social, RequestAuthDto(code)).toToken()
        }.onSuccess {
            Result.success(it)
        }.onFailure {
            Result.failure<Token>(it)
        }

    override suspend fun initToken(accessToken: String, refreshToken: String) {
        TODO("Not yet implemented")
    }

    override suspend fun clearToken() {
        TODO("Not yet implemented")
    }
}
