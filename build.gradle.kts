plugins {
//    kotlin("jvm") version libs.versions.kotlinVersion
    java
}

group = "org.kata.htmlbuilder"
version = "1.0.0-SNAPSHOT"

//kotlin {
//    jvmToolchain(rootProject.libs.versions.jvmVersion.get().toInt())
//    compilerOptions {
//        freeCompilerArgs.addAll("-Xjsr305=strict")
//    }
//}

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "java")

    dependencies {

        implementation(rootProject.libs.kotlin.reflect)
        implementation(rootProject.libs.logging.slf4j)
        testImplementation(rootProject.libs.kotest.core)
        testImplementation(rootProject.libs.kotest.assertions)
        testImplementation(rootProject.libs.kotest.property)
        testImplementation(rootProject.libs.kotest.datest)
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")
        testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    }

    java {
        withSourcesJar()
        toolchain {
            languageVersion = JavaLanguageVersion.of(rootProject.libs.versions.jvmVersion.get())
        }
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = rootProject.libs.versions.jvmVersion.get()
            freeCompilerArgs = listOf("-Xjsr305=strict")
            // Add more compiler options here
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}