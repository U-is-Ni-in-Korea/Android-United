package sopt.uni.data.source.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MyPageResponseDto(
    @SerialName("image")
    val image: String?,
    @SerialName("nickname")
    val nickname: String,
    @SerialName("startDate")
    val startDate: String,
    @SerialName("userId")
    val userId: Int,
)
