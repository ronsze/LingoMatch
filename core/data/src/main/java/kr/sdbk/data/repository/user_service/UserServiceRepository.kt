package kr.sdbk.data.repository.user_service

import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import kr.sdbk.domain.model.user_service.User

interface UserServiceRepository {
    fun getUser(): Flow<FirebaseUser?>
}