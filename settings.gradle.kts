rootProject.name = "plAnt"
include(":plAnt-Mobile")
include(":Core:CoreComposeDesignSystemLib")
include(":Core:CoreResourcesLib")
include(":Core:CoreUtilsLib")
include(":Feature:IdentificationLib")

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    versionCatalogs {
        create("libs") {
            library("kotlin-core", "androidx.core:core-ktx:1.12.0")
            library("kotlin-coroutines-test", "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.0")
            library("androidx-lifecycle", "androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
            library("androidx-lifecycle-compose", "androidx.lifecycle:lifecycle-runtime-compose:2.7.0")
            library("androidx-compose-material", "com.google.android.material:material:1.5.0")
            library("androidx-compose-material3", "androidx.compose.material3:material3:1.2.1")
            library("androidx-compose-tooling", "androidx.compose.ui:ui-tooling:1.6.4")
            library("androidx-fragment", "androidx.fragment:fragment-ktx:1.6.2")
            library("dagger", "com.google.dagger:dagger:2.51.1")
            library("dagger-compiler", "com.google.dagger:dagger-compiler:2.51.1")
            library("google-truth", "com.google.truth:truth:1.2.0")
            library("junit-jupiter-api", "org.junit.jupiter:junit-jupiter-api:5.8.2")
            library("junit-jupiter-engine", "org.junit.jupiter:junit-jupiter-engine:5.8.2")
            library("junit-jupiter-params", "org.junit.jupiter:junit-jupiter-params:5.10.0")
            library("mockk", "io.mockk:mockk:1.13.10")
            library("retrofit2", "com.squareup.retrofit2:retrofit:2.9.0")
            library("retrofit2-converter", "com.squareup.retrofit2:converter-gson:2.9.0")
        }
    }
}

/**
 * use in feature modules this region to implement dependencies:
 *
 *
 * implementation(libs.kotlin.core)
 * implementation(libs.androidx.lifecycle)
 * implementation(libs.androidx.lifecycle.compose)
 * implementation(libs.androidx.compose.material)
 * implementation(libs.androidx.compose.material3)
 * implementation(libs.androidx.fragment)
 * implementation(libs.dagger)
 * implementation(libs.retrofit2)
 * implementation(libs.retrofit2.converter)
 * testImplementation(libs.google.truth)
 * testImplementation(libs.junit.jupiter.api)
 * testImplementation(libs.junit.jupiter.params)
 * testImplementation(libs.mockk)
 * testRuntimeOnly(libs.junit.jupiter.engine)
 * testImplementation(libs.kotlin.coroutines.test)
 * debugImplementation(libs.androidx.compose.tooling)
 * kapt(libs.dagger.compiler)
 *
 * @author Alexandr Chekunkov
 */

/**
 * use this region to implement modules:
 *
 * pattern: implementation(project(":PACKAGE:MODULE_NAME"))
 * example: implementation(project(":Core:CoreComposeDesignSystemLib"))
 *
 * @author Alexandr Chekunkov
 */
