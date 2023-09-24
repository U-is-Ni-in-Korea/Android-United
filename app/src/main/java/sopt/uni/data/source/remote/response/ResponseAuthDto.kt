package sopt.uni.data.source.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import sopt.uni.data.entity.auth.Token

@Serializable
data class ResponseAuthDto(
    @SerialName("access_token")
    val accessToken: String,
    @SerialName("refresh_token")
    val refreshToken: String?,
    @SerialName("couple_id")
    val coupleId: Int?,
) {
    fun toToken(): Token = Token(accessToken = this.accessToken, refreshToken = this.refreshToken)
}
