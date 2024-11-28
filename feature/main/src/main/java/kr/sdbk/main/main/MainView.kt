package kr.sdbk.main.main

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import kr.sdbk.domain.model.user_service.User

@Composable
fun MainView(
    user: User,
    viewModel: MainViewModel = hiltViewModel()
) {
}