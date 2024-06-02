plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "cdr.coreutilslib"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    tasks.withType<Test> { useJUnitPlatform() }
}

dependencies {
    // dependencies for core module region
    implementation(libs.kotlin.core)
    implementation(libs.androidx.lifecycle)
    implementation(libs.androidx.fragment)
    implementation(libs.retrofit2)
    implementation(libs.retrofit2.converter)
    implementation(libs.jwt.worker)
    // end region
}