package kr.sdbk.data.usecase.user_service

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kr.sdbk.data.mapper.UserServiceMapper.toUser
import kr.sdbk.data.repository.user_service.UserServiceRepository
import kr.sdbk.domain.model.user_service.User
import kr.sdbk.domain.usecase.user_service.SignUpUseCase

class SignUpUseCaseImpl(
    private val repository: UserServiceRepository
) : SignUpUseCase {
    override suspend fun invoke(email: String, password: String): User {
        val res = withContext(Dispatchers.IO) {
            repository.signUp(email, password)?.toUser()
        }
        return res ?: throw Exception("User is null")
    }
}