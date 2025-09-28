plugins {
    kotlin("jvm") version "2.1.10"
}

group = "com.yyh"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.springframework.boot:spring-boot-starter-web:3.5.5")}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}