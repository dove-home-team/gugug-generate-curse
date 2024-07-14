import de.undercouch.gradle.tasks.download.Download
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.incremental.createDirectory

plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
    id("de.undercouch.download").version("5.6.0")
    id("com.google.devtools.ksp") version "1.9.22-1.0.17"
}

group = "io.github.dovehometeam"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}
file("libs").createDirectory()

dependencies {
    // Note, if you develop a library, you should use compose.desktop.common.
    // compose.desktop.currentOs should be used in launcher-sourceSet
    // (in a separate module for demo project and in testMain).
    // With compose.desktop.common you will also lose @Preview functionality
    implementation(compose.desktop.currentOs)
    implementation("com.alibaba:easyexcel:4.0.1") {
        exclude(group = "org.apache.commons", module = "commons-compress")
    }
    implementation("org.apache.commons:commons-compress:1.26.2")
    implementation("com.github.doyaaaaaken:kotlin-csv-jvm:1.9.3")
    implementation("com.google.code.gson:gson:2.11.0")
    implementation("cafe.adriel.lyricist:lyricist:1.7.0")

// If you want to use @LyricistStrings to generate code for you
    ksp("cafe.adriel.lyricist:lyricist-processor:1.7.0")

    implementation(fileTree("libs").include("*.jar"))
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "gugug-generate-curse-modrinth"
            packageVersion = "1.0.0"
        }
    }
}


tasks.register<Download>("downloadCMCL") {
    setGroup("gugug")
    dest(file("libs"))
    src(listOf(
        "https://github.com/MrShieh-X/console-minecraft-launcher/releases/download/2.2.1/cmcl.jar"
    ))
}