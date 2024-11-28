import java.util.Properties

plugins {
    alias(libs.plugins.sdbk.application.compose)
    alias(libs.plugins.sdbk.hilt)
    alias(libs.plugins.google.gms)
}

android {
    namespace = "kr.sdbk.lingomatch"

    val secretProperties = Properties().apply {
        load(rootProject.file("secrets.properties").inputStream())
    }

    defaultConfig {
        applicationId = "kr.sdbk.lingomatch"
        targetSdk = 34

        versionCode = 1
        versionName = "1.0"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    signingConfigs {
        create("release") {
            keyAlias = secretProperties.getProperty("KEY_ALIAS")
            keyPassword = secretProperties.getProperty("KEY_PASSWORD")
            storeFile = file(secretProperties.getProperty("STORE_FILE"))
            storePassword = secretProperties.getProperty("STORE_PASSWORD")
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }

        debug {
            isDebuggable = true
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
    implementation(projects.feature.sign)
    implementation(projects.feature.main)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)
}