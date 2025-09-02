import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)

    alias(libs.plugins.devtools.ksp)
}

android {
    namespace = "com.makenshi.inventory"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.makenshi.inventory"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

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
    buildFeatures {
        viewBinding = true
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_11)
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    //Glide
    implementation (libs.glide)
    ksp(libs.glide.compiler)
    //Coroutines
    implementation (libs.kotlinx.coroutines.core)
    implementation (libs.androidx.lifecycle.runtime.ktx)
    // ViewModel
    implementation (libs.androidx.lifecycle.viewmodel.ktx)
    //LiveData
    implementation (libs.androidx.lifecycle.livedata.ktx)
    //Preferences
    implementation (libs.androidx.preference)

    //Robolectric
    testImplementation(libs.robolectric)
    //Hamcrest
    testImplementation(libs.hamcrest)
    //AndroidX Test
    testImplementation(libs.core.ktx)
    testImplementation(libs.androidx.junit.ktx)
    //fragment Testing
    debugImplementation(libs.androidx.fragment.testing.manifest)
    testImplementation(libs.androidx.fragment.testing)
    //Arch Comp
    testImplementation(libs.androidx.core.testing)


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    //Espresso for RecyclerView
    androidTestImplementation( libs.androidx.espresso.contrib)
}