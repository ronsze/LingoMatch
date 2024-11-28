package kr.sdbk.data.mapper

import com.google.firebase.auth.FirebaseUser
import kr.sdbk.domain.model.user_service.User

object UserServiceMapper {
    fun FirebaseUser.toUser() = User(
        uid = uid
    )
}