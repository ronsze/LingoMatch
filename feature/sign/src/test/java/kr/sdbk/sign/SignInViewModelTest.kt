package kr.sdbk.sign

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kr.sdbk.common.exceptions.TooManyRequestsException
import kr.sdbk.common.exceptions.auth.InvalidEmailOrPasswordException
import kr.sdbk.sign.sign_in.SignInViewModel
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class SignInViewModelTest {
    private lateinit var viewModel: SignInViewModel
    private val signInUseCase = MockSignInUseCase()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        val testDispatcher = UnconfinedTestDispatcher()
        Dispatchers.setMain(testDispatcher)
        viewModel = SignInViewModel(testDispatcher, signInUseCase)
    }

    @Test
    fun sign_in_failed_when_email_wrong() = runTest {
        viewModel.signIn("sdbkj", "123123")

        assert(viewModel.uiState.value is SignInViewModel.SignInUiState.Failed)
        assert((viewModel.uiState.value as SignInViewModel.SignInUiState.Failed).error is InvalidEmailOrPasswordException)
    }

    @Test
    fun sign_in_failed_when_password_wrong() = runTest  {
        viewModel.signIn("sdbk", "321321")

        assert(viewModel.uiState.value is SignInViewModel.SignInUiState.Failed)
        assert((viewModel.uiState.value as SignInViewModel.SignInUiState.Failed).error is InvalidEmailOrPasswordException)
    }

    @Test
    fun sign_in_failed_when_too_many_requests() = runTest  {
        viewModel.signIn("sdbkj", "123123")
        viewModel.signIn("sdbkj", "123123")
        viewModel.signIn("sdbkj", "123123")

        assert(viewModel.uiState.value is SignInViewModel.SignInUiState.Failed)
        assert((viewModel.uiState.value as SignInViewModel.SignInUiState.Failed).error is TooManyRequestsException)
    }

    @Test
    fun sign_in_success() = runTest  {
        viewModel.signIn("sdbk", "123123")

        assert(viewModel.uiState.value is SignInViewModel.SignInUiState.Signed)
        assert((viewModel.uiState.value as SignInViewModel.SignInUiState.Signed).user.uid == "123")
    }
}