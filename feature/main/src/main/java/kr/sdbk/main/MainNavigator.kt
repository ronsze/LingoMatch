package kr.sdbk.main

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import kr.sdbk.domain.model.user_service.User
import kr.sdbk.main.main.MainView

fun NavGraphBuilder.mainGraph(

) {
    composable<Main> {
        val user: User = it.toRoute()
        MainView(user)
    }
}

@Serializable
data class Main(val user: User)