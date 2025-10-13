import org.gradle.kotlin.dsl.testImplementation

plugins {
    val kotlinVersion = "1.8.21"

    kotlin("jvm") version "2.1.10"
    kotlin("plugin.spring") version kotlinVersion
    kotlin("plugin.jpa") version kotlinVersion
}

group = "com.yyh"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.springframework.boot:spring-boot-starter-web:3.5.5")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.5.6")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.9.20")

    testImplementation("org.springframework.boot:spring-boot-starter-test:3.5.6")
    testImplementation("com.h2database:h2:2.4.240")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}
