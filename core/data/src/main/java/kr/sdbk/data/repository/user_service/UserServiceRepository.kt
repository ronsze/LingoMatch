package kr.sdbk.data.repository.user_service

import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import kr.sdbk.domain.model.user_service.Profile

interface UserServiceRepository {
    fun getUser(): Flow<FirebaseUser?>
    suspend fun signUp(email: String, password: String): FirebaseUser?
    suspend fun signIn(email: String, password: String): FirebaseUser?
    suspend fun postProfile(profile: Profile)
    suspend fun getProfile(): Profile
}