package kr.sdbk.data.usecase.user_service

import kr.sdbk.data.repository.user_service.UserServiceRepository
import kr.sdbk.domain.model.user_service.Profile
import kr.sdbk.domain.usecase.user_service.GetProfileUseCase

class GetProfileUseCaseImpl(
    private val repository: UserServiceRepository
) : GetProfileUseCase {
    override suspend fun invoke(): Profile = repository.getProfile()
}