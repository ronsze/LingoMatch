package kr.sdbk.sign.sign_up

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
import kr.sdbk.domain.usecase.user_service.SignUpUseCase
import kr.sdbk.sign.exceptions.PasswordNotMatchedException
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val signUpUseCase: SignUpUseCase
) : BaseViewModel() {
    private val _uiState: MutableStateFlow<SignUpUiState> = MutableStateFlow(SignUpUiState.UnSigned)
    val uiState get() = _uiState.asStateFlow()

    fun signUp(
        email: String,
        password: String,
        confirmPassword: String
    ) {
        if (password != confirmPassword) {
            _uiState.set(SignUpUiState.Failed(PasswordNotMatchedException()))
        } else {
            viewModelScope.launch {
                try {
                    val user = withContext(ioDispatcher) {
                        signUpUseCase(email, password)
                    }
                    _uiState.set(SignUpUiState.Signed(user))
                } catch (e: Exception) {
                    _uiState.set(SignUpUiState.Failed(e))
                }
            }
        }
    }

    sealed interface SignUpUiState {
        data object UnSigned: SignUpUiState
        data class Signed(val user: User): SignUpUiState
        data class Failed(val error: Exception): SignUpUiState
    }
}