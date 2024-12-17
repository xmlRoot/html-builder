
plugins {
    kotlin("jvm") version "2.0.21"
}

group = "org.htmlbuilder"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")
    implementation("org.apache.logging.log4j:log4j-api-kotlin:1.5.0")
    runtimeOnly("org.apache.logging.log4j:log4j-core:2.9.1")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}