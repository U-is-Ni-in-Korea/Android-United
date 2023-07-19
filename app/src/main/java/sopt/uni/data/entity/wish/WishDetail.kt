package sopt.uni.data.entity.wish

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestWishDetail(
    @SerialName("wishCouponId")
    val wishCouponId: Int,
)

@Serializable
data class ResponseWishDetail(
    @SerialName("isMine")
    val isMine: Boolean,
    @SerialName("wishCoupon")
    val wishCoupon: WishCoupon,
)