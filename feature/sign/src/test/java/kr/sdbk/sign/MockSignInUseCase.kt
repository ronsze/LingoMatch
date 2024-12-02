package kr.sdbk.sign

import kr.sdbk.common.exceptions.InvalidEmailOrPasswordException
import kr.sdbk.common.exceptions.TooManyRequestsException
import kr.sdbk.domain.model.user_service.User
import kr.sdbk.domain.usecase.user_service.SignInUseCase

class MockSignInUseCase: SignInUseCase {
    private var count: Int = 0

    override suspend fun invoke(email: String, password: String): User {
        count++
        return when {
            email == "sdbk" && password == "123123" -> User("123")
            count >= 3 -> throw TooManyRequestsException()
            else -> throw InvalidEmailOrPasswordException()
        }
    }
}