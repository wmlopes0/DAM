plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.t09practicaentregable"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.t09practicaentregable"
        minSdk = 25
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    //Librería para Retrofit 2
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    //Librería GSON para el tratamiento y conversión de datos JSON
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    //Librería OkHttp para simplificar la construcción de peticiones HTTP
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    //Librería para utilizar corrutinas en Kotlin (peticiones HTTP en segundo plano)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    //Librería Picasso
    implementation ("com.squareup.picasso:picasso:2.71828")
    //========================================================================================
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.6")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.6")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}