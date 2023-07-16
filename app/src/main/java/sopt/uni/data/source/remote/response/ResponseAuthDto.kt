package sopt.uni.data.source.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import sopt.uni.data.entity.auth.Token

@Serializable
data class ResponseAuthDto(
    @SerialName("accessToken")
    val accessToken: String,
    @SerialName("refreshToken")
    val refreshToken: String,
) {
    fun toToken(): Token = Token(accessToken = this.accessToken, refreshToken = this.refreshToken)
}
