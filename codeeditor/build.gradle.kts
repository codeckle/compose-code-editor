import java.io.FileInputStream
import java.util.*

plugins {
    kotlin("multiplatform")
    id("maven-publish")
    id("org.jetbrains.compose")
    id("com.android.library")
    id("org.jetbrains.dokka")
}

group = "com.wakaztahir"
version = "3.0.3"

kotlin {
    android {
        publishLibraryVariants("release")
    }
    jvm("desktop") {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(compose.runtime)
                api(compose.foundation)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {

            }
        }
        val androidTest by getting {
            dependencies {
                implementation("junit:junit:4.13")
            }
        }
        val desktopMain by getting {
            dependencies {
                api(compose.preview)
            }
        }
        val desktopTest by getting
    }
}

android {
    compileSdk = 31
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 21
        targetSdk = 31
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

val githubProperties = Properties()
runCatching { githubProperties.load(FileInputStream(rootProject.file("github.properties"))) }

afterEvaluate {
    publishing {
        repositories {
            maven {
                name = "GithubPackages"
                /** Configure path of your package repository on Github
                 *  Replace GITHUB_USERID with your/organisation Github userID and REPOSITORY with the repository name on GitHub
                 */
                url = uri("https://maven.pkg.github.com/codeckle/compose-code-editor")

                kotlin.runCatching {
                    credentials {
                        /**Create github.properties in root project folder file with gpr.usr=GITHUB_USER_ID  & gpr.key=PERSONAL_ACCESS_TOKEN**/
                        username = (githubProperties["gpr.usr"] ?: System.getenv("GPR_USER")).toString()
                        password = (githubProperties["gpr.key"] ?: System.getenv("GPR_API_KEY")).toString()
                    }
                }.onFailure { it.printStackTrace() }
            }
        }
    }
}