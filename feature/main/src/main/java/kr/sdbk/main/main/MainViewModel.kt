package kr.sdbk.main.main

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kr.sdbk.common.base.BaseViewModel
import kr.sdbk.data.di.IoDispatcher
import kr.sdbk.domain.model.user_service.Profile
import kr.sdbk.domain.usecase.user_service.GetProfileUseCase

class MainViewModel(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val getProfileUseCase: GetProfileUseCase
) : BaseViewModel() {
    private val _uiState: MutableStateFlow<MainUiState> = MutableStateFlow(MainUiState.Loading)
    val uiState get() = _uiState.asStateFlow()

    fun loadData() {
        viewModelScope.launch {
            try {
                val res = withContext(ioDispatcher) {
                    getProfileUseCase()
                }
            } catch (e: Exception) {
                _uiState.set(MainUiState.Failed(e))
            }
        }
    }

    sealed interface MainUiState {
        data object Loading: MainUiState
        data class View(val profile: Profile): MainUiState
        data class Failed(val error: Exception): MainUiState
    }
}