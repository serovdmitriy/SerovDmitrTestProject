object Dependencies {

    object Android {
        const val core = "androidx.core:core-ktx:${Versions.core}"
        const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
        const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragmentKtx}"
        const val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
        const val materialDesign = "com.google.android.material:material:${Versions.materialDesign}"

        const val paging = "androidx.paging:paging-runtime:${Versions.paging}"
        const val maps = "com.google.android.gms:play-services-maps:${Versions.maps}"

        const val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
        const val navigationUIKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"

        const val lifecycleLiveDataRun = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
        const val lifecycleViewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    }

    object Kotlin {
        const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.serialization}"
    }

    object Network {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val retrofitConverterMoshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
        const val okHttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"

        const val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"
        const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
        const val moshiAdapters = "com.squareup.moshi:moshi-adapters:${Versions.moshi}"
        const val moshiKotlinCodegen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"
    }

    object Dagger {
        const val dagger2 = "com.google.dagger:dagger:${Versions.dagger}"
        const val compilerKapt = "com.google.dagger:dagger-compiler:${Versions.dagger}"
        const val componentManager = "com.github.valeryponomarenko.componentsmanager:androidx:${Versions.daggerComponentManager}"
        const val assistedInjectAnnotationsCompile = "com.squareup.inject:assisted-inject-annotations-dagger2:${Versions.daggerAssistedInject}"
        const val assistedInjectProcessorKapt = "com.squareup.inject:assisted-inject-processor-dagger2:${Versions.daggerAssistedInject}"
    }

    object Glide {
        const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
        const val compiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
    }

    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val leakCanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"
    const val binding = "com.github.kirich1409:viewbindingpropertydelegate-noreflection:${Versions.binding}"

    object Room {
        const val room = "androidx.room:room-ktx:${Versions.room}"
        const val roomPaging = "androidx.room:room-paging:${Versions.room}"

        const val compilerRoom = "androidx.room:room-compiler:${Versions.room}"
    }
}
