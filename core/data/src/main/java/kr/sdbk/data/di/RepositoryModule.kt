package kr.sdbk.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.sdbk.data.repository.user_service.UserServiceDataSource
import kr.sdbk.data.repository.user_service.UserServiceRepository
import kr.sdbk.data.repository.user_service.UserServiceRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun providesUserServiceRepository(
        dataSource: UserServiceDataSource
    ): UserServiceRepository = UserServiceRepositoryImpl(dataSource)

    @Provides
    @Singleton
    fun providesUserServiceDataSource() = UserServiceDataSource()
}