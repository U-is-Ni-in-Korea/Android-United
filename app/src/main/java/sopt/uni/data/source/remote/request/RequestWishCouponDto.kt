package sopt.uni.data.source.remote.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestWishCouponDto(
    @SerialName("gameType")
    val gameType: String = "SHORT",
    @SerialName("content")
    val content: String,
)
