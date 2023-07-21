package sopt.uni.data.source.remote.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestUseWish(
    @SerialName("wishCouponId")
    val wishCouponId: Int,
)
