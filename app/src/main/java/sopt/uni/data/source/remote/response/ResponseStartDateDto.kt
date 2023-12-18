package sopt.uni.data.source.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseStartDateDto(
    @SerialName("heartToken")
    val heartToken: Int,
    @SerialName("id")
    val coupleId: Int,
    @SerialName("inviteCode")
    val inviteCode: String,
    @SerialName("startDate")
    val startDate: String,
)
