import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.multiplatformResources)
    alias(libs.plugins.screenshot)
}

multiplatformResources {
    resourcesPackage.set("com.br.kmpdemo.compose.resources")
    resourcesClassName.set("SharedRes")
}

kotlin {
    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    applyDefaultHierarchyTemplate {
        common {
            // Create a group for code that is common to just Android and iOS
            group("mobile") {
                withIos()
                withAndroidTarget()
            }
        }
    }
    androidTarget()
    jvmToolchain(17)
    jvm("desktop")
    task("testClasses")

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.shared)
                implementation(projects.domain)

                // Koin
                implementation(libs.koin.core)

                // Moko
                api(libs.moko.resources)
                api(libs.moko.resources.compose)

                // Jetpack Compose
                implementation(compose.runtime)
                implementation(compose.ui)
                implementation(compose.foundation)
                implementation(compose.animation)
                implementation(compose.material3)
                implementation(compose.components.uiToolingPreview)
                implementation(libs.compose.viewmodel)
                implementation(libs.compose.navigation)

                // Launchpad
                implementation(libs.kmp.launchpad.compose)
                implementation(libs.kmp.launchpad.ai)
                implementation(libs.kmp.launchpad.domain)
                implementation(libs.kmp.launchpad.utils)

                // KotlinX
                implementation(libs.kotlinx.date.time)

                // Serialization
                implementation(libs.ktor.serialization.kotlinx.json)
            }
        }

        val screenshotTest by creating {
            dependencies {
                implementation(libs.ui.tooling)
            }
        }

        val desktopMain by getting {
            dependencies {
                // Compose
                implementation(compose.desktop.currentOs)
            }
        }

        val mobileMain by getting {
            dependencies {
                // Permissions
                implementation(libs.moko.permissions)
                implementation(libs.moko.permissions.compose)

                // Geo
                implementation(libs.moko.geo)
                implementation(libs.moko.geo.compose)
            }
        }

        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by getting {
//            iosX64Main.dependsOn(this)
//            iosArm64Main.dependsOn(this)
//            iosSimulatorArm64Main.dependsOn(this)
        }

        val androidMain by getting {
            dependencies {
                // Koin
                implementation(libs.koin.android)

                // For Android Studio, preview Utils need to be implemented in platform code as they use platform renderers
                implementation(libs.ui.tooling)
                implementation(libs.ui.tooling.preview)

                // Google maps
                implementation(libs.play.services.maps)
                implementation(libs.google.maps.utils)

                // Glance
                implementation(libs.androidx.glance)
                implementation(libs.androidx.glance.appwidget)
                implementation(libs.androidx.glance.material3)
                implementation(libs.androidx.glance.preview)
                implementation(libs.androidx.glance.appwidget.preview)

                // MainActivity - setContent
                implementation(libs.activity.compose)
            }
        }
    }
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

    // Needed for Preview Pane in Android Studio IDE
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.8"
    }
    experimentalProperties["android.experimental.enableScreenshotTest"] = true
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.br.kmpdemo"
            packageVersion = "1.0.0"
        }
    }
}
