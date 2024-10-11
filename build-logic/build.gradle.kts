import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("androidApplicationCompose") {
            id = "sdbk.application.compose"
            implementationClass = "kr.sdbk.buildlogic.ApplicationComposeConvention"
        }

        register("androidLibraryCompose") {
            id = "sdbk.library.compose"
            implementationClass = "kr.sdbk.buildlogic.LibraryComposeConvention"
        }

        register("featureConvention") {
            id = "sdbk.feature"
            implementationClass = "kr.sdbk.buildlogic.FeatureConvention"
        }
    }
}