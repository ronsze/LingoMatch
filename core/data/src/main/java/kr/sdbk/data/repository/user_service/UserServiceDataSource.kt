package kr.sdbk.data.repository.user_service

import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import kr.sdbk.common.exceptions.TooManyRequestsException
import kr.sdbk.common.exceptions.auth.EmailExistsException
import kr.sdbk.common.exceptions.auth.InvalidEmailFormatException
import kr.sdbk.common.exceptions.auth.InvalidEmailOrPasswordException
import kr.sdbk.common.exceptions.auth.WeakPasswordException
import kr.sdbk.domain.model.user_service.Profile

class UserServiceDataSource : UserServiceRepository {
    override fun getUser(): Flow<FirebaseUser?> = flow {
        emit(Firebase.auth.currentUser)
    }

    override suspend fun signUp(email: String, password: String): FirebaseUser? {
        val auth = Firebase.auth
        return try {
            val res = auth.createUserWithEmailAndPassword(email, password).await()
            res.user
        } catch (e: Exception) {
            throw when (e) {
                is FirebaseAuthWeakPasswordException -> WeakPasswordException()
                is FirebaseAuthInvalidCredentialsException -> InvalidEmailFormatException()
                is FirebaseAuthUserCollisionException -> EmailExistsException()
                else -> e
            }
        }
    }

    override suspend fun signIn(email: String, password: String): FirebaseUser? {
        val auth = Firebase.auth
        return try {
            val res = auth.signInWithEmailAndPassword(email, password).await()
            res.user
        } catch (e: Exception) {
            throw when (e) {
                is FirebaseAuthInvalidUserException,
                is FirebaseAuthInvalidCredentialsException -> InvalidEmailOrPasswordException()
                is FirebaseTooManyRequestsException -> TooManyRequestsException()
                else -> e
            }
        }
    }

    override suspend fun postProfile(profile: Profile) {

    }

    override suspend fun getProfile(): Profile {
        return Profile("", 1, 1)
    }
}