package sopt.uni.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.Timer
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TimerModule {

    @Provides
    @Singleton
    fun provideTimer(): Timer {
        return Timer()
    }
}
