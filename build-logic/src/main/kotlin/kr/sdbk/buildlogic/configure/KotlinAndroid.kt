package kr.sdbk.buildlogic.kr.sdbk.buildlogic.configure

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.support.KotlinCompilerOptions

internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        compileSdk = 34

        defaultConfig {
            minSdk = 24

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        KotlinCompilerOptions(
            jvmTarget = JavaVersion.VERSION_11
        )

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }
    }
}