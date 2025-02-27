package kr.sdbk.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kr.sdbk.common.base.TextDialogState
import kr.sdbk.common.ui.composable.dialog.ErrorDialog
import kr.sdbk.domain.model.user_service.User

@Composable
fun SplashView(
    navigateToOnboarding: () -> Unit,
    navigateToMain: (User) -> Unit,
    viewModel: SplashViewModel = hiltViewModel()
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(key1 = lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_CREATE) viewModel.checkUser()
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    var errorDialogState by remember { mutableStateOf(TextDialogState()) }

    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
    LaunchedEffect(key1 = uiState) {
        when (uiState) {
            SplashViewModel.SplashUiState.Loading -> Unit
            is SplashViewModel.SplashUiState.NavigateHome -> navigateToMain(uiState.user)
            SplashViewModel.SplashUiState.NavigateOnboarding -> navigateToOnboarding()
            SplashViewModel.SplashUiState.Failed -> errorDialogState = TextDialogState("Error", true)
        }
    }

    if (errorDialogState.isVisible) {
        ErrorDialog(
            text = errorDialogState.text,
            onClickRetry = {
                viewModel.checkUser()
            },
            onDismissRequest = {
                errorDialogState = TextDialogState()
            }
        )
    }
}