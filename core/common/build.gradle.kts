plugins {
    alias(libs.plugins.sdbk.library.compose)
}

android {
    namespace = "kr.sdbk.common"

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {
    testImplementation(libs.mockk)
    testImplementation(libs.robolectric)
}