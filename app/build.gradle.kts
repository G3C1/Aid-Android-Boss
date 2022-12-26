import java.util.*

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
}

val properties = Properties()
properties.load(project.rootProject.file("local.properties").inputStream())

android {
    namespace = "com.g3c1.aide"
    compileSdk = Versions.CompileSdk

    defaultConfig {
        applicationId = "com.g3c1.aide"
        minSdk = Versions.MinSdk
        targetSdk = Versions.TargetSdk
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        buildConfigField(
            "String",
            "REFRESH_API_URL",
            properties.getProperty("REFRESH_API_URL")
        )
        buildConfigField(
            "String",
            "BASE_URL",
            properties.getProperty("BASE_URL")
        )
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.COMPOSE
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(Dependency.AndroidX.CORE_KTX)
    implementation(Dependency.AndroidX.APP_COMPAT)
    implementation(Dependency.AndroidX.LIFECYCLE)
    implementation(Dependency.Compose.ACTIVITY_COMPOSE)
    implementation(Dependency.Compose.COMPOSE)
    implementation(Dependency.Compose.COMPOSE_LIVEDATA)
    implementation(Dependency.Compose.COMPOSE_TOOLING_PREVIEW)
    implementation(Dependency.Compose.COMPOSE_MATERIAL)
    implementation(Dependency.Compose.COMPOSE_NAV)
    implementation(Dependency.Compose.COMPOSE_ICON)
    testImplementation(Dependency.Test.JUNIT)
    androidTestImplementation(Dependency.AndroidTest.ANDROID_JUNIT)
    androidTestImplementation(Dependency.AndroidTest.ANDROID_ESPRESSO)
    androidTestImplementation(Dependency.ComposeTest.COMPOSE_JUNIT)
    debugImplementation(Dependency.Compose.COMPOSE_TOOLING)
    debugImplementation(Dependency.Compose.COMPOSE_MANIFEST)

    implementation(Dependency.Google.HILT_ANDROID)
    kapt(Dependency.Google.HILT_ANDROID_COMPILER)

    implementation(Dependency.Libraries.RETROFIT)
    implementation(Dependency.Libraries.RETROFIT_CONVERTER_GSON)
    implementation(Dependency.Libraries.OKHTTP)
    implementation(Dependency.Libraries.DATASTORE)
    implementation(Dependency.Libraries.DATASTORE_CORE)
    implementation(Dependency.Libraries.COIL)

    implementation(Dependency.Google.SYSTEM_UI_CONTROLLER)
}