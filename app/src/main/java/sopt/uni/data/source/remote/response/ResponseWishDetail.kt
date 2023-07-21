package sopt.uni.data.source.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseWishDetail(
    @SerialName("isMine")
    val isMine: Boolean,
    @SerialName("nickname")
    val nickname: String,
    @SerialName("wishCoupon")
    val wishCoupon: WishCoupon,
)

@Serializable
data class WishCoupon(
    @SerialName("id")
    val id: Int,
    @SerialName("image")
    val image: String?,
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
