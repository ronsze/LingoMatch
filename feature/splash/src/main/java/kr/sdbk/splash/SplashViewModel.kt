package kr.sdbk.splash

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

fun MathChallenge(num: Int): Int {
    var count: Int = 0
    var res: Int = num

    while (res != 6174) {
        val addedNumber = res.toString().padEnd(4, '0').toCharArray()
        addedNumber
        val ascending = addedNumber.sorted()
        val descending = ascending.reversed()

        res = descending.toString().toInt() - ascending.toString().toInt()

        count++
    }

    return count

}

fun main() {
    println(MathChallenge(readLine()))
}