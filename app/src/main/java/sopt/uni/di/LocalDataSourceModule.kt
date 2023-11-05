package sopt.uni.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import sopt.uni.data.datasource.local.LocalPreferenceDataSource
import sopt.uni.data.datasource.local.LocalPreferenceDataSourceImpl
import sopt.uni.data.datasource.local.SparkleStorage
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataSourceModule {
    @Provides
    @Singleton
    fun provideLocalPreferenceImpl(): LocalPreferenceDataSource =
        LocalPreferenceDataSourceImpl(SparkleStorage)
}
