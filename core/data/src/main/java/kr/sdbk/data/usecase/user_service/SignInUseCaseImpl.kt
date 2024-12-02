package kr.sdbk.data.usecase.user_service

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kr.sdbk.data.mapper.UserServiceMapper.toUser
import kr.sdbk.data.repository.user_service.UserServiceRepository
import kr.sdbk.domain.model.user_service.User
import kr.sdbk.domain.usecase.user_service.SignInUseCase

class SignInUseCaseImpl(
    private val repository: UserServiceRepository
) : SignInUseCase {
    override suspend fun invoke(email: String, password: String): User {
        val res = withContext(Dispatchers.IO) {
            repository.signIn(email, password)?.toUser()
        }
        return res ?: throw Exception("User is null")
    }
}