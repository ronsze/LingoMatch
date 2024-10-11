package kr.sdbk.buildlogic

import com.android.build.api.dsl.LibraryExtension
import kr.sdbk.buildlogic.configure.libs
import kr.sdbk.buildlogic.kr.sdbk.buildlogic.configure.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class FeatureConvention : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("sdbk.library.compose")
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
            }

            dependencies {
                add("implementation", libs.findLibrary("kotlinx.serialization.json").get())
                add("implementation", project(":core:common"))
                add("implementation", project(":core:data"))
                add("implementation", project(":core:domain"))
            }
        }
    }
}