package kr.sdbk.data.repository.user_service

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserServiceDataSource : UserServiceRepository {
    override fun getUser(): Flow<FirebaseUser?> = flow {
        emit(Firebase.auth.currentUser)
    }
}