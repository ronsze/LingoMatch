package kr.sdbk.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.sdbk.data.repository.user_service.UserServiceRepository
import kr.sdbk.data.usecase.user_service.GetUserUseCaseImpl
import kr.sdbk.domain.usecase.user_service.GetUserUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Provides
    @Singleton
    fun providesGetUserUseCase(repository: UserServiceRepository): GetUserUseCase = GetUserUseCaseImpl(repository)
}