package kr.sdbk.data.usecase.user_service

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kr.sdbk.data.mapper.UserServiceMapper.toUser
import kr.sdbk.data.repository.user_service.UserServiceRepository
import kr.sdbk.domain.model.user_service.User
import kr.sdbk.domain.usecase.user_service.GetUserUseCase

class GetUserUseCaseImpl(
    private val repository: UserServiceRepository
) : GetUserUseCase {
    override fun invoke(): Flow<User?> = repository.getUser().map { it?.toUser() }
}