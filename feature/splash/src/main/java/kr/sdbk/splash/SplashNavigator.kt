package kr.sdbk.splash

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

fun NavGraphBuilder.splashGraph(
    navigateToOnboarding: () -> Unit,
    navigateToHome: () -> Unit
) {
    composable<Splash> {
        SplashView(
            navigateToOnboarding = navigateToOnboarding,
            navigateToHome = navigateToHome
        )
    }
}

@Serializable
data object Splash