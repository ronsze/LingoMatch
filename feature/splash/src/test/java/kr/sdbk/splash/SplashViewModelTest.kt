package kr.sdbk.splash

import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import kr.sdbk.domain.model.user_service.User
import kr.sdbk.domain.usecase.user_service.GetUserUseCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class SplashViewModelTest {
    private val getUserUseCase = mockk<GetUserUseCase>()
    private lateinit var viewModel: SplashViewModel

    @Before
    fun setup() {
        viewModel = SplashViewModel(getUserUseCase)
    }

    @Test
    fun user_check_error() = runTest {
        every { getUserUseCase() } returns flow { throw Exception() }
        viewModel.checkUser()

        assert(viewModel.uiState.value is SplashViewModel.SplashUiState.Failed)
    }

    @Test
    fun user_check_not_founded() = runTest {
        every { getUserUseCase() } returns flow { emit(null) }
        viewModel.checkUser()

        assert(viewModel.uiState.value is SplashViewModel.SplashUiState.NavigateOnboarding)
    }
    
    @Test
    fun user_check_founded() = runTest {
        every { getUserUseCase() } returns flow { emit(User("")) }
        viewModel.checkUser()

        assert(viewModel.uiState.value is SplashViewModel.SplashUiState.NavigateHome)
    }
}