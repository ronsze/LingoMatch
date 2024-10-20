plugins {
    alias(libs.plugins.sdbk.feature)
}

android {
    namespace = "kr.sdbk.main"

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }
}