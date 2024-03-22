plugins {
    alias(libs.plugins.androidApplication)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs")
}

android {
    namespace = "com.muratcangzm.blacktooth"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.muratcangzm.blacktooth"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.238481232301"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }

}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)


    //LiveData & LifeCycle
    implementation(libs.lifecycle.viewmodel)
    implementation(libs.lifecycle.livedata)

    //Navigation
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)

    //Dagger Hilt
    implementation (libs.hilt.android)
    kapt(libs.hilt.compiler)


}
kapt {
    correctErrorTypes = true
}