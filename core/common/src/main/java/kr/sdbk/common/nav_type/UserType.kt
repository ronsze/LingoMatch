package kr.sdbk.common.nav_type

import android.os.Bundle
import androidx.navigation.NavType
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kr.sdbk.domain.model.user_service.User

class UserType : NavType<User>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): User? {
        val userJson = bundle.getString(key)
        return if (userJson != null) {
            Json.decodeFromString(userJson)
        } else {
            null
        }
    }

    override fun put(bundle: Bundle, key: String, value: User) {
        bundle.putString(key, Json.encodeToString(value))
    }

    override fun parseValue(value: String): User {
        return Json.decodeFromString(value)
    }

    override fun serializeAsValue(value: User): String {
        return Json.encodeToString(value)
    }
}