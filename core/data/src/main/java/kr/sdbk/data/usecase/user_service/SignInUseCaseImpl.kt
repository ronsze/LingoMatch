package kr.sdbk.data.usecase.user_service

import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kr.sdbk.common.exceptions.InvalidEmailOrPasswordException
import kr.sdbk.common.exceptions.TooManyRequestsException
import kr.sdbk.data.mapper.UserServiceMapper.toUser
import kr.sdbk.data.repository.user_service.UserServiceRepository
import kr.sdbk.domain.model.user_service.User
import kr.sdbk.domain.usecase.user_service.SignInUseCase

class SignInUseCaseImpl(
    private val repository: UserServiceRepository
) : SignInUseCase {
    override suspend fun invoke(email: String, password: String): User {
        val res = withContext(Dispatchers.IO) {
            try {
                repository.signIn(email, password)?.toUser()
            } catch (e: Exception) {
                throw when (e) {
                    is FirebaseAuthInvalidUserException,
                    is FirebaseAuthInvalidCredentialsException -> InvalidEmailOrPasswordException()
                    is FirebaseTooManyRequestsException -> TooManyRequestsException()
                    else -> e
                }
            }
        }
        return res ?: throw Exception("User is null")
    }
}