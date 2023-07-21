package sopt.uni.data.repository.wish

import retrofit2.Response
import sopt.uni.data.source.remote.response.ResponseAllWish
import sopt.uni.data.source.remote.response.ResponseWishDetail

interface WishRepository {
    suspend fun getWishList(userId: Int): Result<ResponseAllWish>

    suspend fun getWishDetail(wishCouponId: Int): Result<ResponseWishDetail>

    suspend fun patchCreateWish(gameType: String, content: String): Response<Unit>

    suspend fun patchUseWish(wishCouponId: Int): Response<Unit>
}
