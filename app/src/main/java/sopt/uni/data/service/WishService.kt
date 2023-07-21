package sopt.uni.data.service

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path
import sopt.uni.data.source.remote.request.RequestWishCouponDto
import sopt.uni.data.source.remote.response.ResponseAllWish
import sopt.uni.data.source.remote.response.ResponseWishDetail

interface WishService {
    @GET("api/user/{userId}/wish")
    suspend fun getWishList(@Path("userId") useId: Int): ResponseAllWish

    @GET("/api/wish/{wishCouponId}")
    suspend fun getWishDetail(@Path("wishCouponId") wishCouponId: Int): ResponseWishDetail

    @PATCH("/api/wish")
    suspend fun patchCreateWish(@Body request: RequestWishCouponDto): Response<Unit>

    @PATCH("/api/wish/{wishCouponId}")
    suspend fun patchUseWish(@Path("wishCouponId") wishCouponId: Int): Response<Unit>
}
