package sopt.uni.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import sopt.uni.data.service.AuthService
import sopt.uni.data.service.HistoryService
import sopt.uni.data.service.HomeService
import sopt.uni.data.service.MyPageService
import sopt.uni.data.service.OnBoardingService
import sopt.uni.data.service.ShortGameService
import sopt.uni.data.service.WishService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    private inline fun <reified T> Retrofit.create(): T = this.create(T::class.java)

    @Provides
    @Singleton
    fun provideHistoryApi(retrofit: Retrofit): HistoryService =
        retrofit.create(HistoryService::class.java)

    @Provides
    @Singleton
    fun provideMyPageApi(retrofit: Retrofit): MyPageService =
        retrofit.create(MyPageService::class.java)

    @Provides
    @Singleton
    fun provideAuthService(retrofit: Retrofit): AuthService = retrofit.create()

    @Provides
    @Singleton
    fun provideOnBoardingService(retrofit: Retrofit): OnBoardingService = retrofit.create()

    @Provides
    @Singleton
    fun provideShortGameService(retrofit: Retrofit): ShortGameService = retrofit.create()

    @Provides
    @Singleton
    fun provideHomeService(retrofit: Retrofit): HomeService = retrofit.create()

    @Provides
    @Singleton
    fun provideWishService(retrofit: Retrofit): WishService = retrofit.create()
}
