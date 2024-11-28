package kr.sdbk.data.repository.user_service

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class UserServiceDataSource : UserServiceRepository {
    override fun getUser(): Flow<FirebaseUser?> = flow {
        emit(Firebase.auth.currentUser)
    }

    override suspend fun signUp(email: String, password: String): FirebaseUser? {
        val auth = Firebase.auth
        val res = auth.createUserWithEmailAndPassword(email, password).await()
        return res.user
    }

    override suspend fun signIn(email: String, password: String): FirebaseUser? {
        val auth = Firebase.auth
        val res = auth.signInWithEmailAndPassword(email, password).await()
        return res.user
    }
}