package kr.sdbk.data.usecase.user_service

import kr.sdbk.data.repository.user_service.UserServiceRepository
import kr.sdbk.domain.model.user_service.Profile
import kr.sdbk.domain.usecase.user_service.PostProfileUseCase

class PostProfileUseCaseImpl(
    private val repository: UserServiceRepository
) : PostProfileUseCase {
    override suspend fun invoke(profile: Profile) = repository.postProfile(profile)
}