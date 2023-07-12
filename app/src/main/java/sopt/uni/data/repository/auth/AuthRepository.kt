package sopt.uni.data.repository.auth

interface AuthRepository {
    suspend fun loginByKakao(token: String)
}
