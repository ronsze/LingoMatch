package kr.sdbk.splash

import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kr.sdbk.common.base.BaseViewModel
import kr.sdbk.common.base.TextDialogState
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
                    Log.e("qweqwe", "${it}")
                    _uiState.emit(SplashUiState.Failed)
                }
                .collect { user ->
                    user?.run {
                        loadData()
                    } ?: _uiState.emit(SplashUiState.NavigateOnboarding)
                }
        }
    }

    private fun loadData() {
        _uiState.set(SplashUiState.NavigateHome)
    }

    sealed interface SplashUiState {
        data object Loading: SplashUiState
        data object NavigateHome: SplashUiState
        data object NavigateOnboarding: SplashUiState
        data object Failed: SplashUiState
    }
}