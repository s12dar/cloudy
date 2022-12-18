@file:Suppress("PackageDirectoryMismatch")

object Dependencies {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlinVersion}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtxVersion}"
    const val junit4 = "junit:junit:${Versions.junit4Version}"
    const val junitExtensions = "androidx.test.ext:junit:${Versions.junitExtensionsVersion}"
    const val junitExtensionsKtx = "androidx.test.ext:junit-ktx:${Versions.junitExtensionsVersion}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCoreVersion}"
    const val daggerHilt = "com.google.dagger:hilt-android:${Versions.daggerHiltVersion}"
    const val extendedMaterialIcons =
        "androidx.compose.material:material-icons-extended:${Versions.materialIconsVersion}"
    const val daggerHiltCompiler =
        "com.google.dagger:hilt-android-compiler:${Versions.daggerHiltVersion}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
    const val retrofitMoshiConverter =
        "com.squareup.retrofit2:converter-moshi:${Versions.retrofitVersion}"
    const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttpVersion}"
    const val okHttpLoggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.okHttpVersion}"
    const val truth = "com.google.truth:truth:${Versions.truthVersion}"
    const val mockk = "io.mockk:mockk:${Versions.mockkVersion}"
    const val androidArchCoreTest =
        "androidx.arch.core:core-testing:${Versions.androidArchCoreTest}"
    const val coroutinesTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesVersion}"
    const val composeMaterial = "androidx.compose.material:material:${Versions.composeVersion}"
    const val composeAnimation = "androidx.compose.animation:animation:${Versions.composeVersion}"
    const val composeUi = "androidx.compose.ui:ui:${Versions.composeVersion}"
    const val composeToolingPreview =
        "androidx.compose.ui:ui-tooling-preview:${Versions.composeVersion}"
    const val composeTooling = "androidx.compose.ui:ui-tooling-preview:${Versions.composeVersion}"
    const val composeActivity =
        "androidx.activity:activity-compose:${Versions.composeActivityVersion}"
    const val composeViewModel =
        "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.composeViewModelVersion}"
    const val composeLiveData =
        "androidx.compose.runtime:runtime-livedata:${Versions.composeVersion}"
    const val ktxLiveData =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.livedataKtxVersion}"
    const val composeUiTest = "androidx.compose.ui:ui-test-junit4:${Versions.composeVersion}"
    const val composeConstraintLayout =
        "androidx.constraintlayout:constraintlayout-compose:${Versions.composeConstraintLayoutVersion}"
    const val hiltCompiler = "androidx.hilt:hilt-compiler:${Versions.hiltVersion}"
    const val hiltNavigationCompose =
        "androidx.hilt:hilt-navigation-compose:${Versions.hiltNavigationCompose}"
    const val accompanistSwipeRefresh =
        "com.google.accompanist:accompanist-swiperefresh:${Versions.accompanistSwipeRefresh}"
    const val roomRuntime = "androidx.room:room-runtime:${Versions.roomVersion}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.roomVersion}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.roomVersion}"
    const val lifecycleRuntimeKtx =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleRuntimeVersion}"
    const val testMonitor = "androidx.test:monitor:${Versions.testMonitorVersion}"
    const val testRunner = "androidx.test:runner:${Versions.testRunnerVersion}"
    const val coreLibraryDesugaring =
        "com.android.tools:desugar_jdk_libs:${Versions.coreDesugarVersion}"
    const val locationServices =
        "com.google.android.gms:play-services-location:${Versions.locationServicesVersion}"
    const val stetho = "com.facebook.stetho:stetho:${Versions.stethoVersion}"
    const val stethoOkHttp3 = "com.facebook.stetho:stetho-okhttp3:${Versions.stethoVersion}"
    const val datastorePreferences =
        "androidx.datastore:datastore-preferences:${Versions.datastoreVersion}"
    const val datastoreCore = "androidx.datastore:datastore-core:${Versions.datastoreVersion}"
    const val squareLogcat = "com.squareup.logcat:logcat:${Versions.logcatVersion}"
    const val flowCombineTuple =
        "com.github.Zhuinden:flow-combinetuple-kt:${Versions.combineTupleVersion}"
    const val testManifest = "androidx.compose.ui:ui-test-manifest:${Versions.testMonitorVersion}"
}
