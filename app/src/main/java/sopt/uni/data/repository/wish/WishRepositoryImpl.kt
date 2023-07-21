package sopt.uni.data.repository.wish

import kotlinx.serialization.json.JsonNull.content
import retrofit2.Response
import sopt.uni.data.service.WishService
import sopt.uni.data.source.remote.request.RequestUseWish
import sopt.uni.data.source.remote.request.RequestWishCouponDto
import sopt.uni.data.source.remote.response.ResponseAllWish
import sopt.uni.data.source.remote.response.ResponseWishDetail
import javax.inject.Inject

class WishRepositoryImpl @Inject constructor(
    private val wishService: WishService,
) : WishRepository {
    override suspend fun getWishList(userId: Int): Result<ResponseAllWish> =
        kotlin.runCatching {
            wishService.getWishList(userId)
        }.onSuccess {
            Result.success(it)
        }.onFailure {
            Result.failure<List<ResponseAllWish>>(it)
        }

    override suspend fun getWishDetail(wishCouponId: Int): Result<ResponseWishDetail> =
        kotlin.runCatching {
            wishService.getWishDetail(wishCouponId)
        }.onSuccess {
            Result.success(it)
        }.onFailure {
            Result.failure<List<ResponseWishDetail>>(it)
        }

    override suspend fun patchCreateWish(gameType: String, content: String): Response<Unit> {
        return wishService.patchCreateWish(RequestWishCouponDto(gameType, content))
    }

    override suspend fun patchUseWish(wishCouponId: Int): Result<Unit> =
        kotlin.runCatching {
            wishService.patchUseWish(RequestUseWish(wishCouponId)).body() ?: Unit
        }.onSuccess {
            Result.success(Unit)
        }.onFailure {
            Result.failure<Unit>(it)
        }
}
