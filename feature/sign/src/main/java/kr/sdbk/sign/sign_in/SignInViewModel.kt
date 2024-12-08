package kr.sdbk.sign.sign_in

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kr.sdbk.common.base.BaseViewModel
import kr.sdbk.data.di.IoDispatcher
import kr.sdbk.domain.model.user_service.User
import kr.sdbk.domain.usecase.user_service.SignInUseCase
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val signInUseCase: SignInUseCase
) : BaseViewModel() {
    private val _uiState: MutableStateFlow<SignInUiState> = MutableStateFlow(SignInUiState.UnSigned)
    val uiState get() = _uiState.asStateFlow()

    fun signIn(
        email: String,
        password: String
    ) {
        viewModelScope.launch {
            try {
                val user = withContext(ioDispatcher) {
                    signInUseCase(email, password)
                }
                _uiState.set(SignInUiState.Signed(user))
            } catch (e: Exception) {
                _uiState.set(SignInUiState.Failed(e))
            }
        }
    }

    sealed interface SignInUiState {
        data object UnSigned: SignInUiState
        data class Signed(val user: User): SignInUiState
        data class Failed(val error: Exception): SignInUiState
    }
}