package kr.sdbk.main.main

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kr.sdbk.common.base.TextDialogState
import kr.sdbk.common.ui.composable.dialog.ErrorDialog
import kr.sdbk.domain.model.user_service.Profile
import kr.sdbk.domain.model.user_service.User

@Composable
fun MainView(
    user: User,
    viewModel: MainViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
    val errorDialogState by remember { mutableStateOf(TextDialogState()) }
    Box {
        when (uiState) {
            MainViewModel.MainUiState.Loading -> LoadingView()
            is MainViewModel.MainUiState.View -> View(uiState.profile)
            is MainViewModel.MainUiState.Failed -> ErrorDialog("Error", {}) { }
        }
    }
}

@Composable
private fun LoadingView() {

}

@Composable
private fun View(
    profile: Profile
) {

}