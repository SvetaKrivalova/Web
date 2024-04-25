plugins {
    kotlin("jvm") version "1.9.22"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    implementation("org.springframework:spring-core:5.3.15")
    implementation("org.springframework:spring-context:5.3.15")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}