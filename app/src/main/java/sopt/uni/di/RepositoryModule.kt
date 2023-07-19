package sopt.uni.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import sopt.uni.data.repository.auth.AuthRepository
import sopt.uni.data.repository.auth.AuthRepositoryImpl
import sopt.uni.data.repository.history.HistoryRepository
import sopt.uni.data.repository.history.HistoryRepositoryImpl
import sopt.uni.data.repository.mypage.MypageRepository
import sopt.uni.data.repository.mypage.MypageRepositoryImpl
import sopt.uni.data.repository.onboarding.OnBoardingRepository
import sopt.uni.data.repository.onboarding.OnBoardingRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideAuthRepository(authRepository: AuthRepositoryImpl): AuthRepository = authRepository

    @Provides
    @Singleton
    fun provideOnBoardingRepository(onBoardingRepository: OnBoardingRepositoryImpl): OnBoardingRepository =
        onBoardingRepository

    @Provides
    @Singleton
    fun providesHistoryRepository(historyRepository: HistoryRepositoryImpl): HistoryRepository =
        historyRepository

    @Provides
    @Singleton
    fun providesMyPageRepository(mypageRepository: MypageRepositoryImpl): MypageRepository =
        mypageRepository
}
