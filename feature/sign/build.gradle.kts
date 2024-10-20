plugins {
    alias(libs.plugins.sdbk.feature)
}

android {
    namespace = "kr.sdbk.sign"

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }
}