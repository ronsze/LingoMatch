package kr.sdbk.buildlogic.kr.sdbk.buildlogic.configure

import org.gradle.api.Plugin
import org.gradle.api.Project

class HiltConvention : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }
        }
    }
}