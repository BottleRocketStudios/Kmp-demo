//import org.jetbrains.compose.desktop.application.dsl.TargetFormat

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
    androidTarget()

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

                // Moko
                implementation(libs.moko.permissions)
                implementation(libs.moko.permissions.compose)
                api(libs.moko.resources)
                api(libs.moko.resources.compose)

                // Jetpack Compose
                implementation(compose.runtime)
                implementation(compose.ui)
                implementation(compose.foundation)
                implementation(compose.animation)
                implementation(compose.material3)
//                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
//                implementation(compose.components.resources)

                implementation(compose.components.uiToolingPreview)



                // PreCompose - https://github.com/Tlaster/PreCompose
                implementation(libs.precompose)
                implementation(libs.precompose.viewmodel)
                implementation(libs.precompose.koin)

                // Launchpad
                implementation(libs.kmp.launchpad.compose)
                implementation(libs.kmp.launchpad.ai)
                implementation(libs.kmp.launchpad.domain)
                implementation(libs.kmp.launchpad.utils)

                // KotlinX
                implementation(libs.kotlinx.date.time)
            }
        }
//        commonTest.dependencies {
//            implementation(libs.kotlin.test)
//        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by getting {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }

        val androidMain by getting {
            dependsOn(commonMain)
            dependencies {
                // Koin
                implementation(libs.koin.android)

                // For Android Studio, preview Utils need to be implemented in platform code as they use platform renderers
                implementation(libs.ui.tooling.preview)

                // Google maps
                implementation(libs.play.services.maps)
                implementation(libs.google.maps.utils)

                // Glance
                implementation(libs.glance)
                implementation(libs.glance.appwidget)
                implementation(libs.glance.material3)

                // MainActivity - setContent
                implementation(libs.activity.compose)
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

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }



    // Needed for Preview Pane in IDE
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.4"
    }
}
dependencies {
    debugImplementation(libs.androidx.ui.tooling)
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
