package sopt.uni.data.entity.wish

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WishCoupon(
    @SerialName("id")
    val id: Int,
    @SerialName("image")
    val image: String,
    @SerialName("content")
    val content: String?,
    @SerialName("isVisible")
    val isVisible: Boolean,
    @SerialName("isUsed")
    val isUsed: Boolean,
    @SerialName("usedAt")
    val usedAt: String?,
    @SerialName("gameType")
    val gameType: String,
)
