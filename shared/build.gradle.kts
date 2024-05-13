plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.ktLint)
    alias(libs.plugins.multiplatformResources)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.ksp)
}

multiplatformResources {
    multiplatformResourcesPackage = "com.br.kmpdemo.resources"
    multiplatformResourcesClassName = "SharedRes"
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
                implementation(projects.domain)
                implementation(projects.data)

                // PreCompose - https://github.com/Tlaster/PreCompose
                implementation(libs.precompose.viewmodel)

                // Moko
                api(libs.moko.resources)
                api(libs.moko.resources.compose)

                // KTOR Networking and Serialization
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.content.negotiation)
                implementation(libs.ktor.serialization.kotlinx.json)
                implementation(libs.koin.core)

                //  https://github.com/cashapp/sqldelight/issues/4357
                //    This fixes issue in  version of Koin which is pulling older version of Stately
                implementation(libs.stately.common)

                // Date Time
                implementation(libs.kotlinx.date.time)
                implementation(libs.androidx.core.i18n)

                // KMP
                implementation(libs.kmp.launchpad.domain)
                implementation(libs.kmp.launchpad.utils)
                implementation(libs.kmp.launchpad.google.utils)
                implementation(libs.kmp.launchpad.ai)
            }
        }
        val androidMain by getting {
            dependsOn(commonMain)
            dependencies {
                // Ktor
                implementation(libs.ktor.client.android)

                // Koin
                implementation(libs.koin.android)

                // Utility
                implementation(libs.google.maps)
                implementation(libs.google.maps.utils)
                implementation(libs.google.places)
                implementation(libs.play.services.maps)

            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation(libs.ktor.client.darwin)
            }
        }
        commonTest.dependencies {
//                implementation(kotlin("test"))
            implementation(libs.koin.test)
            implementation(libs.moko.resources.test)
        }
        val desktopMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation(libs.kotlinx.date.time)
            }
        }
    }

}

android {
    with(libs.versions) {
        compileSdk = compile.sdk.get().toInt()
        namespace = "${application.id.get()}.shared"
        defaultConfig {
            minSdk = min.sdk.get().toInt()
        }
    }


//  TODO  Check to see if this is needed
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

//  TODO  Check to see if this is needed
    // Needed for Preview Pane in IDE
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.4"
    }

}