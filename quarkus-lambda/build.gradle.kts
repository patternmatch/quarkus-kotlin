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
    implementation("io.quarkus:quarkus-amazon-lambda")
    implementation("io.quarkus:quarkus-kotlin")

    testCompile("io.quarkus:quarkus-junit5:0.22.0")
}

tasks.register<Zip>("lambdaNativeZip")
tasks {
    "test"(Test::class) {
        useJUnitPlatform()
    }
    "buildNative"(QuarkusNative::class) {
        isEnableHttpUrlHandler = true
        graalvmHome = "/usr/lib/jvm/graalvm"
    }
    "lambdaNativeZip"(Zip::class) {
        from("$buildDir")
        include("*-runner")
        from("$buildDir/wiring-classes")
        include("bootstrap").fileMode = 775
    }
}

allOpen {
    annotations("javax.enterprise.context.ApplicationScoped", "io.quarkus.runtime.annotations.RegisterForReflection")
}

quarkus {
    setSourceDir("src/main/kotlin")
    setOutputDirectory("build/classes/kotlin/main")
}
