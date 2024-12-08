package kr.sdbk.domain.usecase.user_service

import kr.sdbk.domain.model.user_service.Profile

interface PostProfileUseCase {
    suspend operator fun invoke(profile: Profile)
}