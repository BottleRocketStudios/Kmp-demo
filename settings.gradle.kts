pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
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

rootProject.name = "KMP_Demo"
include(":androidApp")
include(":shared")
include(":domain")
include(":composeApp")
include(":data")