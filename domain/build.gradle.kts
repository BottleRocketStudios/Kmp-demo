plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinSerialization)
}

kotlin {
    applyDefaultHierarchyTemplate()
    androidTarget()
    jvmToolchain(17)
    jvm("desktop")
    task("testClasses")

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                // Ktor
                implementation(libs.ktor.serialization.kotlinx.json)

                // Koin
                implementation(libs.koin.core)

                // Kotlinx
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