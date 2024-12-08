package kr.sdbk.domain.usecase.user_service

import kr.sdbk.domain.model.user_service.Profile

interface GetProfileUseCase {
    suspend operator fun invoke(): Profile
}