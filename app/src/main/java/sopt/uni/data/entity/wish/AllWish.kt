package sopt.uni.data.entity.wish

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestAllWish(
    @SerialName("usedId")
    val usedId: Int,
)

@Serializable
data class ResponseAllWish(
    @SerialName("availableWishCoupon")
    val availableWishCoupon: Int,
    @SerialName("newWishCoupon")
    val newWishCoupon: Int,
    @SerialName("wishCouponList")
    val wishCouponList: List<WishCoupon>,
)
