import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.multiplatformResources)
    alias(libs.plugins.ksp)
}

multiplatformResources {
    multiplatformResourcesPackage = "com.br.kmpdemo.compose.resources"
    multiplatformResourcesClassName = "SharedRes"
}

kotlin {
    applyDefaultHierarchyTemplate()
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }

    jvmToolchain(17)

    // TODO - uncomment to enable desktop
//    jvm("desktop")


    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "compose"
            export(libs.moko.resources)
        }
    }

    sourceSets {
        // TODO - uncomment to enable desktop
//        val desktopMain by getting

        val commonMain by getting {
            dependencies {
                implementation(project(":domain"))
                implementation(project(":shared"))

                // Koin
                implementation(libs.koin.core)

                // Moko permissions
                api(libs.moko.permissions)
                api(libs.moko.permissions.compose)

                // Jetpack Compose
                implementation(compose.runtime)
                implementation(compose.ui)
                implementation(compose.foundation)
                implementation(compose.animation)
                implementation(compose.material3)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)

                // PreCompose - https://github.com/Tlaster/PreCompose
                api(libs.precompose)
                api(libs.precompose.viewmodel)
                api(libs.precompose.koin)
                api(libs.moko.resources)
                api(libs.moko.resources.compose)

                // Launchpad
                implementation(libs.kmp.launchpad.ai)

                // Logger
                implementation(libs.kermit.logger)

                // KotlinX
                implementation(libs.kotlinx.date.time)
            }
        }
//        commonTest.dependencies {
//            implementation(libs.kotlin.test)
//        }
        val androidMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation(libs.koin.android)


                implementation(libs.ui.tooling.preview.android)
                implementation(libs.play.services.coroutines)
                implementation(libs.play.services.maps)
                implementation(libs.google.maps.utils)

                // Preview Utils need to be implemented in platform code as they use platform renderers
                implementation(compose.preview)
                implementation(compose.uiTooling)

                //  TODO - check to see if these deps are needed from this point down
//                api(compose.foundation)
//                api(compose.animation)
//                api(libs.precompose)
//                api(libs.precompose.viewmodel)
//                api(libs.precompose.koin)
//                api(libs.moko.resources)
//                api(libs.moko.resources.compose)
//
//                implementation(compose.foundation)
//                implementation(compose.material3)
//                implementation(compose.materialIconsExtended)
//                implementation(compose.runtime)
//                implementation(compose.ui)
//
//                // KMP
//                implementation(libs.kmp.launchpad.compose)
//                implementation(libs.kmp.launchpad.domain)
//                implementation(libs.kmp.launchpad.utils)
//
//                // Utility
//                implementation(libs.google.maps)
//                implementation(libs.google.maps.utils)
//                implementation(libs.google.places)
//                implementation(libs.play.services.maps)
//                implementation(libs.glance)
//                implementation(libs.glance.appwidget)
//                implementation(libs.glance.material3)
//                implementation(libs.kermit.logger)

            }
        }
    }

    task("testClasses")
}


android {
    with(libs.versions) {
        namespace = "${application.id.get()}.compose"
        compileSdk = compile.sdk.get().toInt()

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

    // Needed for Preview Pane in IDE
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.4"
    }
}

// TODO - uncomment to enable desktop
//compose.desktop {
//    application {
//        mainClass = "MainKt"
//
//        nativeDistributions {
//            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
//            packageName = "com.br.kmpdemo"
//            packageVersion = "1.0.0"
//        }
//    }
//}
