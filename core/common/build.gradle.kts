plugins {
    alias(libs.plugins.sdbk.library.compose)
    id("kotlinx-serialization")
}

android {
    namespace = "kr.sdbk.common"

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {
    implementation(projects.core.domain)
    implementation(libs.kotlinx.serialization.json)
}