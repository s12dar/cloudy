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

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
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
}