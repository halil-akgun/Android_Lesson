plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)

    // Plugin for generating SafeArgs classes used in Android Navigation Component
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.android_lesson"
    compileSdk = 35

    // Enable view binding
    buildFeatures {
        viewBinding = true
    }

    defaultConfig {
        applicationId = "com.android_lesson"
        minSdk = 24
        targetSdk = 34
        versionCode = 3
        versionName = "1.2"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // for navigation component
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    // Preferences DataStore
    implementation(libs.androidx.datastore.preferences)
    // Lifecycle components & Kotlin Coroutines components
    implementation(libs.androidx.lifecycle.livedata.ktx)
    api (libs.kotlinx.coroutines.core)
    api (libs.kotlinx.coroutines.android)
}