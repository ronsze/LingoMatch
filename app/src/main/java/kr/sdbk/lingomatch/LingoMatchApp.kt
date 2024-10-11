package kr.sdbk.lingomatch

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import kr.sdbk.splash.Splash
import kr.sdbk.splash.splashGraph

@Composable
fun LingoMatchApp(
    modifier: Modifier,
    navHostController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navHostController,
        startDestination = Splash,
        modifier = modifier
    ) {
        splashGraph()
    }
}