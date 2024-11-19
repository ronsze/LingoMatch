package kr.sdbk.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.sdbk.data.repository.user_service.UserServiceRepository
import kr.sdbk.data.usecase.user_service.GetUserUseCaseImpl
import kr.sdbk.data.usecase.user_service.SignInUseCaseImpl
import kr.sdbk.data.usecase.user_service.SignUpUseCaseImpl
import kr.sdbk.domain.usecase.user_service.GetUserUseCase
import kr.sdbk.domain.usecase.user_service.SignInUseCase
import kr.sdbk.domain.usecase.user_service.SignUpUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Provides
    @Singleton
    fun providesGetUserUseCase(repository: UserServiceRepository): GetUserUseCase = GetUserUseCaseImpl(repository)

    @Provides
    @Singleton
    fun providesSignUpUseCase(repository: UserServiceRepository): SignUpUseCase = SignUpUseCaseImpl(repository)

    @Provides
    @Singleton
    fun providesSignInUseCase(repository: UserServiceRepository): SignInUseCase = SignInUseCaseImpl(repository)
}