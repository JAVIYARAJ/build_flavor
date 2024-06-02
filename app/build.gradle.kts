plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.example.buildflavor"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.buildflavor"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            buildConfigField("String", "BASE_URL", "\"https://api.production.example.com/\"")
            signingConfig = signingConfigs.getByName("debug")
        }
        create("beta") {
            isMinifyEnabled = false
            applicationIdSuffix = ".beta"
            buildConfigField("String", "BASE_URL", "\"https://api.beta.example.com/\"")
            signingConfig = signingConfigs.getByName("debug")
            resValue("string", "app_name", "BuildFlavorBeta")
        }
    }

    flavorDimensions+="paid_beta"

    productFlavors {
        create("free") {
            dimension = "paid_beta"
            applicationIdSuffix = ".free"
        }
        create("paid") {
            dimension = "paid_beta"
            applicationIdSuffix = ".paid"
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
}