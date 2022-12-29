object Dependency {

    object AndroidX {
        const val APP_COMPAT = "androidx.appcompat:appcompat:${Versions.APP_COMPAT}"
        const val CORE_KTX = "androidx.core:core-ktx:${Versions.CORE_KTX}"
        const val LIFECYCLE = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.LIFECYCLE}"
    }

    object Compose {
        const val ACTIVITY_COMPOSE =
            "androidx.activity:activity-compose:${Versions.ACTIVITY_COMPOSE}"
        const val COMPOSE = "androidx.compose.ui:ui:${Versions.COMPOSE}"
        const val COMPOSE_TOOLING_PREVIEW =
            "androidx.compose.ui:ui-tooling-preview:${Versions.COMPOSE}"
        const val COMPOSE_MATERIAL = "androidx.compose.material:material:${Versions.COMPOSE}"
        const val COMPOSE_TOOLING = "androidx.compose.ui:ui-tooling:${Versions.COMPOSE}"
        const val COMPOSE_MANIFEST = "androidx.compose.ui:ui-test-manifest:${Versions.COMPOSE}"
        const val COMPOSE_LIVEDATA = "androidx.compose.runtime:runtime-livedata:${Versions.COMPOSE}"
        const val COMPOSE_NAV = "androidx.navigation:navigation-compose:${Versions.COMPOSE_NAV}"
        const val COMPOSE_ICON =
            "androidx.compose.material:material-icons-extended:${Versions.COMPOSE}"
    }

    object Google {
        const val HILT_ANDROID = "com.google.dagger:hilt-android:${Versions.HILT}"
        const val HILT_ANDROID_COMPILER = "com.google.dagger:hilt-android-compiler:${Versions.HILT}"
        const val SYSTEM_UI_CONTROLLER =
            "com.google.accompanist:accompanist-systemuicontroller:${Versions.SYSTEM_UI_CONTROLLER}"
    }

    object Test {
        const val JUNIT = "junit:junit:${Versions.JUNIT}"
    }

    object AndroidTest {
        const val ANDROID_JUNIT = "androidx.test.ext:junit:${Versions.ANDROID_JUNIT}"
        const val ANDROID_ESPRESSO = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO}"
    }

    object ComposeTest {
        const val COMPOSE_JUNIT = "androidx.compose.ui:ui-test-junit4:${Versions.COMPOSE}"
    }

    object Libraries {
        const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
        const val RETROFIT_CONVERTER_GSON =
            "com.squareup.retrofit2:converter-gson:${Versions.RETROFIT}"
        const val OKHTTP = "com.squareup.okhttp3:okhttp:${Versions.OKHTTP}"
        const val OKHTTP_LOGGING_INTERCEPTOR =
            "com.squareup.okhttp3:logging-interceptor:${Versions.OKHTTP}"
        const val DATASTORE = "androidx.datastore:datastore-preferences:${Versions.DATASTORE}"
        const val DATASTORE_CORE =
            "androidx.datastore:datastore-preferences-core:${Versions.DATASTORE}"
        const val COIL = "io.coil-kt:coil-compose:${Versions.COIL}"
    }
}