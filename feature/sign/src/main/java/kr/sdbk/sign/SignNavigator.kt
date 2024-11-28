package kr.sdbk.sign

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import kr.sdbk.domain.model.user_service.User
import kr.sdbk.sign.onboarding.OnboardingView
import kr.sdbk.sign.sign_in.SignInView
import kr.sdbk.sign.sign_up.SignUpView

fun NavGraphBuilder.signGraph(
    navigateToMain: (User) -> Unit,
    navController: NavController
) {
    composable<Onboarding> {
        OnboardingView(
            navigateToSignUp = { navController.navigate(SignUp) },
            navigateToSignIn = { navController.navigate(SignIn) }
        )
    }

    composable<SignUp> {
        SignUpView(
            navigateToMain = navigateToMain
        )
    }

    composable<SignIn> {
        SignInView(
            navigateToMain = navigateToMain
        )
    }
}

@Serializable
data object Onboarding

@Serializable
data object SignUp

@Serializable
data object SignIn