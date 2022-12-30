buildscript {
    dependencies {
        classpath(Dependency.Google.HILT_ANDROID_GRADLE)
    }
}// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id(Dependency.GradlePlugins.ANDROID_APPLICATION) version Versions.ANDROID apply false
    id(Dependency.GradlePlugins.ANDROID_LIBRARY) version Versions.ANDROID apply false
    id(Dependency.GradlePlugins.KOTLIN) version Versions.KOTLIN apply false
}