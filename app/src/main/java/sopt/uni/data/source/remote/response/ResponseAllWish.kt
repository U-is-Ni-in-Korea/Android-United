package sopt.uni.data.source.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseAllWish(
    @SerialName("availableWishCoupon")
    val availableWishCoupon: Int,
    @SerialName("newWishCoupon")
    val newWishCoupon: Int,
    @SerialName("wishCouponList")
    val wishCouponList: List<WishCoupon>,
)
