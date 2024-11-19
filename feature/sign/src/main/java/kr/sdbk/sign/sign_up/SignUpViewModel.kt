package kr.sdbk.sign.sign_up

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kr.sdbk.common.base.BaseViewModel
import kr.sdbk.domain.model.user_service.User
import kr.sdbk.domain.usecase.user_service.SignUpUseCase
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
) : BaseViewModel() {
    private val _uiState: MutableStateFlow<SignUpUiState> = MutableStateFlow(SignUpUiState.UnSigned)
    val uiState get() = _uiState.asStateFlow()

    private val _errorMessage: MutableStateFlow<String> = MutableStateFlow("")
    val errorMessage get() = _errorMessage.asStateFlow()

    fun signUp(
        email: String,
        password: String,
        confirmPassword: String
    ) {
        if (password != confirmPassword) {
            _errorMessage.set("Passwords not matched")
        } else {
            viewModelScope.launch {
                try {
                    val user = signUpUseCase(email, password)
                    _uiState.set(SignUpUiState.Signed(user))
                } catch (e: Exception) {
                    _errorMessage.set(e.message.toString())
                }
            }

        }
    }

    sealed interface SignUpUiState {
        data object UnSigned: SignUpUiState
        data class Signed(val user: User): SignUpUiState
    }
}