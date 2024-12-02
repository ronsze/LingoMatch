package kr.sdbk.sign

import kr.sdbk.common.exceptions.auth.EmailExistsException
import kr.sdbk.common.exceptions.auth.InvalidEmailFormatException
import kr.sdbk.common.exceptions.auth.WeakPasswordException
import kr.sdbk.sign.exceptions.PasswordNotMatchedException
import kr.sdbk.sign.sign_up.SignUpViewModel
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class SignUpViewModelTest {
    private lateinit var viewModel: SignUpViewModel
    private val signUpUseCase = MockSignUpUseCase()

    @Before
    fun setup() {
        viewModel = SignUpViewModel(signUpUseCase)
    }

    @Test
    fun sign_up_failed_when_password_not_matched() {
        viewModel.signUp("sdbkj@aa.com", "1231231", "12323")

        assert(viewModel.uiState.value is SignUpViewModel.SignUpUiState.Failed)
        assert((viewModel.uiState.value as SignUpViewModel.SignUpUiState.Failed).error is PasswordNotMatchedException)
    }

    @Test
    fun sign_up_failed_when_weak_password() {
        viewModel.signUp("sdbkj@aa.com", "123123", "123123")

        assert(viewModel.uiState.value is SignUpViewModel.SignUpUiState.Failed)
        assert((viewModel.uiState.value as SignUpViewModel.SignUpUiState.Failed).error is WeakPasswordException)
    }

    @Test
    fun sign_up_failed_when_email_exists() {
        viewModel.signUp("sdbk@aa.com", "1231231", "1231231")

        assert(viewModel.uiState.value is SignUpViewModel.SignUpUiState.Failed)
        assert((viewModel.uiState.value as SignUpViewModel.SignUpUiState.Failed).error is EmailExistsException)
    }

    @Test
    fun sign_up_failed_when_invalid_email_format() {
        viewModel.signUp("sdbkj", "1231231", "1231231")

        assert(viewModel.uiState.value is SignUpViewModel.SignUpUiState.Failed)
        assert((viewModel.uiState.value as SignUpViewModel.SignUpUiState.Failed).error is InvalidEmailFormatException)
    }

    @Test
    fun sign_up_success() {
        viewModel.signUp("sdbkj@aa.com", "1231231", "1231231")

        assert(viewModel.uiState.value is SignUpViewModel.SignUpUiState.Signed)
        assert((viewModel.uiState.value as SignUpViewModel.SignUpUiState.Signed).user.uid == "123")
    }
}