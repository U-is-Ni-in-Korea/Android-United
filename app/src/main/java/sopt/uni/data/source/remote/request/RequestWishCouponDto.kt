package sopt.uni.data.source.remote.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestWishCouponDto(
    @SerialName("content")
    val content: String,
    @SerialName("gameType")
    val gameType: String = "SHORT",
)
