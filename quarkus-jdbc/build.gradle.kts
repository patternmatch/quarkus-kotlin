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
    implementation("io.quarkus:quarkus-resteasy")
    implementation("io.quarkus:quarkus-kotlin")
    implementation("io.quarkus:quarkus-agroal")
    implementation("io.quarkus:quarkus-jdbc-h2")
    implementation("io.quarkus:quarkus-flyway")

    testCompile("io.quarkus:quarkus-junit5:0.22.0")
    testCompile("io.rest-assured:rest-assured:3.3.0")
}

tasks {
    "test"(Test::class) {
        useJUnitPlatform()
    }
}

allOpen {
    annotations("javax.ws.rs.Path", "javax.enterprise.context.ApplicationScoped")
}

quarkus {
    setSourceDir("src/main/kotlin")
    setOutputDirectory("build/classes/kotlin/main")
}
