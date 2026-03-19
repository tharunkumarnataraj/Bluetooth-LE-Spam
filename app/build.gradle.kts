plugins { 
    id("com.android.application") 
    id("org.jetbrains.kotlin.android") 
    id("kotlin-kapt")
}

android { 
    namespace = "com.example.blespam" 
    compileSdk = 34 

    defaultConfig { 
        applicationId = "com.example.blespam" 
        minSdk = 26 
        targetSdk = 34 
        versionCode = 1 
        versionName = "1.0" 
    }

    buildTypes { 
        getByName("debug") { 
            isMinifyEnabled = false 
        } 
    }

    compileOptions { 
        sourceCompatibility = JavaVersion.VERSION_17 
        targetCompatibility = JavaVersion.VERSION_17 
    }

    kotlinOptions { 
        jvmTarget = "17" 
    }

    buildFeatures {
        dataBinding = true
    }
}

dependencies { 
    implementation("androidx.core:core-ktx:1.12.0") 
    implementation("androidx.appcompat:appcompat:1.6.1") 
    implementation("com.google.android.material:material:1.11.0") 
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7") 
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7") 
    implementation("com.airbnb.android:lottie:6.0.0") 
    implementation("androidx.preference:preference-ktx:1.2.1")
    
    // Room database dependencies
    implementation("androidx.room:room-runtime:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
}