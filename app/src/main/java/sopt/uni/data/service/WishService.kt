package sopt.uni.data.service

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path
import sopt.uni.data.source.remote.request.RequestCreateWish
import sopt.uni.data.source.remote.request.RequestUseWish
import sopt.uni.data.source.remote.response.ResponseAllWish
import sopt.uni.data.source.remote.response.ResponseWishDetail

interface WishService {
    @GET("api/user/{userId}/wish")
    suspend fun getWishList(@Path("userId") useId: Int): ResponseAllWish

    @GET("/api/wish/{wishCouponId}")
    suspend fun getWishDetail(@Path("wishCouponId") wishCouponId: Int): ResponseWishDetail

    @PATCH("/api/wish")
    suspend fun patchCreateWish(@Body request: RequestCreateWish): Response<Unit>

    @PATCH("/api/wish/{wishCouponId}")
    suspend fun patchUseWish(@Body request: RequestUseWish): Response<Unit>
}
