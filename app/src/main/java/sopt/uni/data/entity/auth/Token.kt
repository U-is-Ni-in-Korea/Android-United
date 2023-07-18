package sopt.uni.data.entity.auth

data class Token(
    val accessToken: String = "",
    val refreshToken: String? = "",
)
