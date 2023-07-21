package sopt.uni.data.entity.wish

import sopt.uni.data.source.remote.response.WishCoupon

data class WishMultiData(
    val type: Int = -1,
    val newWishCoupon: Int? = null,
    val wishCoupon: WishCoupon? = null,
)
