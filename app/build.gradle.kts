plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.example.cinematest"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.cinematest"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }



}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation (libs.androidx.lifecycle.viewmodel.compose)

    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.legacy.support.v4)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.espresso.core)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)


    implementation(project.dependencies.platform(libs.koin.bom))
    implementation(libs.koin.core)
    implementation(libs.koin.core.viewmodel.v400)
    implementation(libs.insert.koin.koin.android)
    runtimeOnly(libs.koin.compose)
    implementation(libs.koin.compose.viewmodel)
  //  implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1") // for Lifecycle support


    //   implementation (libs.koin.android.viewmodel)
    //implementation (libs.koin.android)

    // https://mavenlibs.com/maven/dependency/com.squareup.moshi/moshi
    implementation(libs.moshi)
    implementation (libs.moshi.kotlin)
    implementation(libs.converter.moshi)
    ksp(libs.moshi.kotlin.codegen)

    implementation (libs.okhttp3.okhttp)
    implementation (libs.okhttp3.logging.interceptor)
    implementation(libs.logging.interceptor)

    implementation (libs.gson)
    implementation (libs.retrofit)
    implementation (libs.converter.gson)

    /**
     * An OkHttp interceptor which logs HTTP request and response data.
     */
    implementation(libs.okhttp3.logging.interceptor)

    //Glide
    implementation (libs.glide)
    runtimeOnly(libs.compose)
    implementation(libs.landscapist.glide)

    // Skip this if you don't want to use integration libraries or configure Glide.
    annotationProcessor (libs.compiler)

    implementation (libs.coil.compose)
    implementation(libs.coil3.coil.compose)




}