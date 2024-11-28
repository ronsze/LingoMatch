package kr.sdbk.domain.usecase.user_service

import kr.sdbk.domain.model.user_service.User

interface SignUpUseCase {
    suspend operator fun invoke(
        email: String,
        password: String
    ): User
}