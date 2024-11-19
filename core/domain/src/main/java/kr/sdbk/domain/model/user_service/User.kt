package kr.sdbk.domain.model.user_service

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val uid: String
)