plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinSerialization)
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
            baseName = "domain"
        }
    }

//    jvm()

    sourceSets {
        val commonMain by getting {
            dependencies {
                //put your multiplatform dependencies here
                implementation(libs.ktor.serialization.kotlinx.json)
                implementation(libs.koin.core)
                implementation(libs.kotlinx.date.time)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(libs.koin.test)
            }
        }
    }

    task("testClasses")
}

android {
    with(libs.versions) {
        namespace = "${application.id.get()}.domain"
        compileSdk = compile.sdk.get().toInt()
        defaultConfig {
            minSdk = min.sdk.get().toInt()
        }
    }
}