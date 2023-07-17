package sopt.uni.data.source.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResPonseNickNameDto(
    @SerialName("couple")
    val couple: Couple,
    @SerialName("id")
    val id: Int,
    @SerialName("image")
    val image: String,
    @SerialName("nickname")
    val nickname: String,
) {
    @Serializable
    data class Couple(
        @SerialName("heartToken")
        val heartToken: Int,
        @SerialName("id")
        val id: Int,
        @SerialName("inviteCode")
        val inviteCode: String,
        @SerialName("startDate")
        val startDate: String,
    )
}
