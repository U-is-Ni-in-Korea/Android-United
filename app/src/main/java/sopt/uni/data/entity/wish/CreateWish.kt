package sopt.uni.data.entity.wish

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestCreateWish(
    @SerialName("gameType")
    val gameType: Int,
    @SerialName("content")
    val content: String,
)
