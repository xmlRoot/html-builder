import org.gradle.kotlin.dsl.`kotlin-dsl`

plugins {
    `kotlin-dsl`
}

extra.apply {
    set("kotlinVersion", libs.versions.kotlinVersion.get())
}

dependencies {
    implementation(kotlin("gradle-plugin", libs.versions.kotlinVersion.get()))
    implementation(kotlin("allopen", libs.versions.kotlinVersion.get()))
}

repositories {
    mavenCentral()
    mavenLocal()
    gradlePluginPortal()
}