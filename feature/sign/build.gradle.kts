plugins {
    alias(libs.plugins.sdbk.feature)
    alias(libs.plugins.sdbk.hilt)
}

android {
    namespace = "kr.sdbk.sign"

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {
    implementation(libs.hilt.compose)
}