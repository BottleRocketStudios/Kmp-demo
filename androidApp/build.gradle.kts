plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.jetbrainsCompose)
}

kotlin {
    androidTarget()
    sourceSets {
        val androidMain by getting {
            dependencies {
                implementation(project(":shared"))
                implementation(project(":domain"))
//                implementation(project(":compose"))
                implementation(libs.activity.compose)
                implementation(libs.koin.android)

                api(compose.foundation)
                api(compose.animation)
                api(libs.precompose)
                api(libs.precompose.viewmodel)
                api(libs.precompose.koin)
                api(libs.moko.resources)
                api(libs.moko.resources.compose)

                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.materialIconsExtended)
                implementation(compose.runtime)
                implementation(compose.ui)

                // KMP
                implementation(libs.kmp.launchpad.compose)
                implementation(libs.kmp.launchpad.domain)
                implementation(libs.kmp.launchpad.utils)

                // Utility
                implementation(libs.google.maps)
                implementation(libs.google.maps.utils)
                implementation(libs.google.places)
                implementation(libs.play.services.maps)
                implementation(libs.glance)
                implementation(libs.glance.appwidget)
                implementation(libs.glance.material3)
                implementation(libs.kermit.logger)
            }
        }
    }
}

android {
    with(libs.versions) {
        namespace = application.id.get()
        compileSdk = compile.sdk.get().toInt()

        sourceSets["main"].apply {
            res.srcDirs("src/androidMain/res", "src/commonMain/resources")
            manifest.srcFile("src/androidMain/AndroidManifest.xml")
        }

        defaultConfig {
            applicationId = application.id.get()
            minSdk = min.sdk.get().toInt()
            targetSdk = target.sdk.get().toInt()
            versionCode = version.code.get().toInt()
            versionName = version.name.get()
            manifestPlaceholders["MAPS_API_KEY"] = "MAPS_API_KEY"
        }
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(17)
    }
}