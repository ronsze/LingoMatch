package kr.sdbk.sign

import kotlinx.coroutines.test.runTest
import kr.sdbk.common.exceptions.auth.InvalidEmailOrPasswordException
import kr.sdbk.common.exceptions.TooManyRequestsException
import kr.sdbk.sign.sign_in.SignInViewModel
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class SignInViewModelTest {
    private lateinit var viewModel: SignInViewModel
    private val signInUseCase = MockSignInUseCase()

    @Before
    fun setup() {
        viewModel = SignInViewModel(signInUseCase)
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