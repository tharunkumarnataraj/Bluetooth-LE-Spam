android { 
    ... 
    defaultConfig { 
        ... 
        minSdk = 26  // Updated from 21 to 26
    } 
}
dependencies { 
    implementation("androidx.preference:preference-ktx:1.2.1")  // Added preference-ktx dependency
    ... 
}