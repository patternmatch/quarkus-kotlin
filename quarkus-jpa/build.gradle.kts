import io.quarkus.gradle.tasks.QuarkusNative

group = "com.patternmatch"
version = "1.0-SNAPSHOT"

plugins {
    kotlin("jvm") version "1.3.41"
    id("io.quarkus") version "0.22.0"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.3.41"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(enforcedPlatform("io.quarkus:quarkus-bom:0.22.0"))
    implementation("io.quarkus:quarkus-resteasy-jackson")
    implementation("io.quarkus:quarkus-kotlin")
    implementation("io.quarkus:quarkus-hibernate-orm")
    implementation("io.quarkus:quarkus-jdbc-h2")

    testCompile("io.quarkus:quarkus-junit5:0.22.0")
    testCompile("io.rest-assured:rest-assured:3.3.0")
}

tasks {
    "test"(Test::class) {
        useJUnitPlatform()
    }
    "buildNative"(QuarkusNative::class) {
        isEnableHttpUrlHandler = true
        graalvmHome = "/usr/lib/jvm/graalvm"
    }
}

allOpen {
    annotations("javax.ws.rs.Path", "javax.enterprise.context.ApplicationScoped", "javax.persistence.Entity")
}

quarkus {
    setSourceDir("src/main/kotlin")
    setOutputDirectory("build/classes/kotlin/main")
}
