package kr.sdbk.sign.sign_in

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kr.sdbk.common.base.BaseViewModel
import kr.sdbk.domain.model.user_service.User
import kr.sdbk.domain.usecase.user_service.SignInUseCase
import kr.sdbk.sign.sign_up.SignUpViewModel.SignUpUiState
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase
) : BaseViewModel() {
    private val _uiState: MutableStateFlow<SignInUiState> = MutableStateFlow(SignInUiState.UnSigned)
    val uiState get() = _uiState.asStateFlow()

    private val _errorMessage: MutableStateFlow<String> = MutableStateFlow("")
    val errorMessage get() = _errorMessage.asStateFlow()

    fun signIn(
        email: String,
        password: String
    ) {
        viewModelScope.launch {
            try {
                val user = signInUseCase(email, password)
                _uiState.set(SignInUiState.Signed(user))
            } catch (e: Exception) {
                _errorMessage.set(e.message.toString())
            }
        }
    }

    sealed interface SignInUiState {
        data object UnSigned: SignInUiState
        data class Signed(val user: User): SignInUiState
    }
}