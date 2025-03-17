import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    id("org.springframework.boot") version "3.4.3"
    id("io.spring.dependency-management") version "1.1.7"
}

kotlin {
    jvmToolchain(21)
}

allprojects {
    apply(plugin = "kotlin")
    repositories {
        mavenCentral()
    }
    group = "github.jhchee.otel"
    version = "0.0.1-SNAPSHOT"
    kotlin {
        compilerOptions {
            freeCompilerArgs.addAll("-Xjsr305=strict")
        }
    }
}

subprojects {
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    dependencyManagement {
        imports {
            // required by opentelemetry-spring-boot-starter
            mavenBom("io.opentelemetry.instrumentation:opentelemetry-instrumentation-bom:2.13.1")
            mavenBom("io.awspring.cloud:spring-cloud-aws-dependencies:3.2.0")
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

tasks {
    withType<BootJar> {
        enabled = false
    }
    withType<Jar> {
        enabled = false
    }
}