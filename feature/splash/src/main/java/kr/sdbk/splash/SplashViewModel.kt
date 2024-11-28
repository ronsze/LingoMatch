package kr.sdbk.splash

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kr.sdbk.common.base.BaseViewModel
import kr.sdbk.domain.model.user_service.User
import kr.sdbk.domain.usecase.user_service.GetUserUseCase
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase
) : BaseViewModel() {
    private val _uiState = MutableStateFlow<SplashUiState>(SplashUiState.Loading)
    val uiState get() = _uiState.asStateFlow()

    fun checkUser() {
        viewModelScope.launch {
            getUserUseCase()
                .catch {
                    _uiState.emit(SplashUiState.Failed)
                }
                .collect { user ->
                    user?.run {
                        loadData(this)
                    } ?: _uiState.emit(SplashUiState.NavigateOnboarding)
                }
        }
    }

    private fun loadData(user: User) {
        _uiState.set(SplashUiState.NavigateHome(user))
    }

    sealed interface SplashUiState {
        data object Loading: SplashUiState
        data class NavigateHome(val user: User): SplashUiState
        data object NavigateOnboarding: SplashUiState
        data object Failed: SplashUiState
    }
}