plugins {
    alias(libs.plugins.sdbk.application.compose)
}

android {
    namespace = "kr.sdbk.lingomatch"

    defaultConfig {
        applicationId = "kr.sdbk.lingomatch"
        targetSdk = 34

        versionCode = 1
        versionName = "1.0"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.data)
    implementation(projects.core.domain)

    implementation(projects.feature.splash)
}