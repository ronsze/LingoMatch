package kr.sdbk.buildlogic

import com.android.build.api.dsl.ApplicationExtension
import kr.sdbk.buildlogic.configure.configureCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class LibraryComposeConvention : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            val extension = extensions.getByType<ApplicationExtension>()
            configureCompose(extension)
        }
    }
}