package sopt.uni.data.entity.wish

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestUseWish(
    @SerialName("wishCouponId")
    val wishCouponId: Int,
)
