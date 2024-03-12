plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
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
        val commonMain by getting {
            dependencies {
                implementation(project(":domain"))

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

                // Logger
                implementation(libs.kermit.logger)
            }
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        val androidMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation(libs.ui.tooling.preview.android)
                implementation(libs.play.services.coroutines)
                implementation(libs.play.services.maps)
                implementation(libs.google.maps.utils)

                // Preview Utils need to be implemented in platform code as they use platform renderers
                implementation(compose.preview)
                implementation(compose.uiTooling)
            }
        }
    }
}


android {
    with(libs.versions) {
        namespace = "${application.id.get()}.compose"
        compileSdk = compile.sdk.get().toInt()
        defaultConfig {
            minSdk = min.sdk.get().toInt()
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
