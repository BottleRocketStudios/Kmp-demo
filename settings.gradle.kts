rootProject.name = "KMP_Demo"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
//        mavenLocal()
        maven {
            url = uri("https://maven.pkg.github.com/BottleRocketStudios/kmp-demo")
            credentials {
                username = System.getenv("REPO_READ_USER")
                password = System.getenv("REPO_READ_TOKEN")
            }
        }
        google()
        mavenCentral()
    }
}

include(":data")
include(":domain")
include(":shared")
include(":composeApp")
include(":wearapp")
