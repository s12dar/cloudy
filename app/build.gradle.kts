plugins {
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    kotlin("android")
    kotlin("kapt")
}

android {
    namespace = Configs.applicationId
    compileSdk = Configs.compileSdkVersion

    defaultConfig {
        applicationId = Configs.applicationId
        minSdk = Configs.minSdkVersion
        targetSdk = Configs.targetSdkVersion
        versionCode = Configs.versionCode
        versionName = Configs.versionName

        testInstrumentationRunner = Configs.testInstrumentationRunner
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
            )
        }
    }

    android {
        sourceSets {
            getByName("test").java.srcDir("src/sharedTest/java")        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeVersion
    }

    buildFeatures {
        compose = true
    }

    testOptions {
        unitTests.isIncludeAndroidResources = true
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {
    implementation(project(":core-ui"))
    implementation(project(":core-navigation"))

    implementation(Dependencies.coreKtx)
    implementation(Dependencies.composeUi)
    implementation(Dependencies.composeMaterial)
    implementation(Dependencies.composeToolingPreview)
    implementation(Dependencies.composeLiveData)
    implementation(Dependencies.lifecycleRuntimeKtx)
    implementation(Dependencies.composeActivity)
    implementation(Dependencies.testMonitor)
    testImplementation(Dependencies.junit4)
    androidTestImplementation(Dependencies.testRunner)
    testImplementation(Dependencies.truth)
    testImplementation(Dependencies.mockk)
    androidTestImplementation(Dependencies.junitExtensions)
    androidTestImplementation(Dependencies.espressoCore)
    testImplementation(Dependencies.androidArchCoreTest)
    testImplementation(Dependencies.coroutinesTest)
    androidTestImplementation(Dependencies.composeUiTest)
    debugImplementation(Dependencies.composeTooling)
    coreLibraryDesugaring(Dependencies.coreLibraryDesugaring)

    //Dagger - Hilt
    implementation(Dependencies.daggerHilt)
    kapt(Dependencies.daggerHiltCompiler)
    kapt(Dependencies.hiltCompiler)
    implementation(Dependencies.hiltNavigationCompose)

    // Location Services
    implementation(Dependencies.locationServices)

    // Retrofit
    implementation(Dependencies.retrofit)
    implementation(Dependencies.retrofitMoshiConverter)
    implementation(Dependencies.okHttpLoggingInterceptor)

    // Lifecycle libs
    implementation(Dependencies.composeViewModel)
    implementation(Dependencies.ktxLiveData)

    // Room
    implementation(Dependencies.roomRuntime)
    kapt(Dependencies.roomCompiler)

    // Kotlin Extensions and Coroutines support for Room
    implementation(Dependencies.roomKtx)

    // Stetho
    implementation(Dependencies.stetho)
    implementation(Dependencies.stethoOkHttp3)

    // Accompanist swipe refresh
    implementation(Dependencies.accompanistSwipeRefresh)

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.0")
    implementation("androidx.activity:activity-compose:1.6.1")
    implementation(platform("androidx.compose:compose-bom:2023.01.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.01.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    implementation("androidx.compose.material3:material3:1.0.1")

    implementation("com.google.android.material:material:1.8.0")

    // Dagger hilt
    implementation("com.google.dagger:hilt-android:2.44.2")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    kapt("androidx.hilt:hilt-compiler:1.0.0")
    kapt("com.google.dagger:hilt-android-compiler:2.44.2")


    // OkHttp3
    implementation(platform("com.squareup.okhttp3:okhttp-bom:4.10.0"))
    implementation("com.squareup.okhttp3:okhttp")
    implementation("com.squareup.okhttp3:logging-interceptor")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    // Moshi
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")

    // Extended icons
    implementation("androidx.compose.material:material-icons-extended:1.4.0")

    // Foundation
    implementation("androidx.compose.foundation:foundation:1.4.0")

    implementation("androidx.compose.material3:material3-window-size-class:1.0.1")


    // Constraint layout
    implementation(Dependencies.composeConstraintLayout)
    implementation(Dependencies.extendedMaterialIcons)

    // DataStore
    implementation(Dependencies.datastorePreferences)
    implementation(Dependencies.datastoreCore)

    // Logcat
    implementation(Dependencies.squareLogcat)

    // LiveData-CombineTuple
    implementation(Dependencies.flowCombineTuple)

    implementation("androidx.compose.material3:material3:1.0.1")


    implementation("com.google.accompanist:accompanist-navigation-animation:0.29.0-alpha")
    implementation("androidx.core:core-splashscreen:1.0.0")
}