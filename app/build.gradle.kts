plugins {
    kotlin("kapt")
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "tech.jubin.flipper.demo"
    compileSdk = 34

    defaultConfig {
        applicationId = "tech.jubin.flipper.demo"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        javaCompileOptions {
            annotationProcessorOptions {
                arguments["room.schemaLocation"] = "$projectDir/schemas"
            }
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
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

    debugImplementation(libs.flipper)
    debugImplementation(libs.soloader)
    debugImplementation(libs.flipper.network.plugin)
    releaseImplementation(libs.flipper.noop)
    debugImplementation(libs.flipper.leakcanary2.plugin)
    debugImplementation(libs.flipper.fresco.plugin)
    debugImplementation(libs.flipper.leakcanary2.plugin)
    debugImplementation(libs.leakcanary.android)
    debugImplementation(libs.thread.inspector.flipper)
//    debugImplementation(libs.thread.inspector.bhook)
    debugImplementation(libs.flipper.backstack)


    implementation(libs.okhttp)
    implementation(libs.moshi.kotlin)
    kapt(libs.moshi.kotlin.codegen)
    implementation(libs.androidx.room.runtime)
    kapt(libs.androidx.room.compiler.v242)

    annotationProcessor(libs.androidx.room.compiler)

}