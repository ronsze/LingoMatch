package kr.sdbk.main

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import kr.sdbk.common.nav_type.UserType
import kr.sdbk.domain.model.user_service.User
import kr.sdbk.main.main.MainView
import kotlin.reflect.typeOf

fun NavGraphBuilder.mainGraph(

) {
    composable<Main>(
        typeMap = mapOf(typeOf<User>() to UserType())
    ) {
        val user = it.toRoute<Main>().user
        MainView(user)
    }
}

@Serializable
data class Main(val user: User)