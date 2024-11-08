package kr.sdbk.buildlogic

import kr.sdbk.buildlogic.configure.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class HiltConvention : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("kotlin-kapt")
                apply("com.google.dagger.hilt.android")
            }

            dependencies {
                add("implementation", libs.findLibrary("hilt").get())
                add("kapt", libs.findLibrary("hilt-compiler").get())
            }
        }
    }
}