package kr.sdbk.sign

import android.util.Patterns
import kr.sdbk.common.exceptions.auth.EmailExistsException
import kr.sdbk.common.exceptions.auth.InvalidEmailFormatException
import kr.sdbk.common.exceptions.auth.WeakPasswordException
import kr.sdbk.domain.model.user_service.User
import kr.sdbk.domain.usecase.user_service.SignUpUseCase

class MockSignUpUseCase: SignUpUseCase {
    override suspend fun invoke(email: String, password: String): User {
        return when {
            password.length <= 6 -> throw WeakPasswordException()
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> throw InvalidEmailFormatException()
            email == "sdbk@aa.com" -> throw EmailExistsException()
            else -> User("123")
        }
    }
}