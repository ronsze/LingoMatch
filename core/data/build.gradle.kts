plugins {
    alias(libs.plugins.sdbk.library.compose)
}

android {
    namespace = "kr.sdbk.data"

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }
}