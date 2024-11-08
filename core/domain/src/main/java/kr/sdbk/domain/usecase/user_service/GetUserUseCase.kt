package kr.sdbk.domain.usecase.user_service

import kotlinx.coroutines.flow.Flow
import kr.sdbk.domain.model.user_service.User

interface GetUserUseCase {
    operator fun invoke(): Flow<User?>
}