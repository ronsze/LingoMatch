package kr.sdbk.data.repository.user_service

import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import kr.sdbk.domain.model.user_service.Profile

class UserServiceRepositoryImpl(
    private val dataSource: UserServiceDataSource
) : UserServiceRepository {
    override fun getUser(): Flow<FirebaseUser?> = dataSource.getUser()
    override suspend fun signUp(email: String, password: String): FirebaseUser? = dataSource.signUp(email, password)
    override suspend fun signIn(email: String, password: String): FirebaseUser? = dataSource.signIn(email, password)
    override suspend fun postProfile(profile: Profile) = dataSource.postProfile(profile)
    override suspend fun getProfile(): Profile = dataSource.getProfile()
}