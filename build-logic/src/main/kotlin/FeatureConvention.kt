package kr.sdbk.buildlogic

import kr.sdbk.buildlogic.configure.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class FeatureConvention : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("sdbk.library.compose")
                apply("kotlinx-serialization")
            }

            dependencies {
                add("implementation", libs.findLibrary("kotlinx.serialization.json").get())
                add("implementation", libs.findLibrary("mockk").get())
                add("implementation", project(":core:common"))
                add("implementation", project(":core:data"))
                add("implementation", project(":core:domain"))
            }
        }
    }
}