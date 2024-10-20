plugins {
    alias(libs.plugins.sdbk.feature)
}

android {
    namespace = "kr.sdbk.splash"

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }
}