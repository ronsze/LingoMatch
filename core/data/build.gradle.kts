plugins {
    alias(libs.plugins.sdbk.library)
    alias(libs.plugins.sdbk.hilt)
    alias(libs.plugins.google.gms)
}

android {
    namespace = "kr.sdbk.data"

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.domain)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)

    testImplementation(libs.mockk)
    testImplementation(libs.robolectric)
}