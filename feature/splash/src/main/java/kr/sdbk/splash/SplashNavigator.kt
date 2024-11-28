package kr.sdbk.splash

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import kr.sdbk.domain.model.user_service.User

fun NavGraphBuilder.splashGraph(
    navigateToOnboarding: () -> Unit,
    navigateToMain: (User) -> Unit
) {
    composable<Splash> {
        SplashView(
            navigateToOnboarding = navigateToOnboarding,
            navigateToMain = navigateToMain
        )
    }
}

@Serializable
data object Splash