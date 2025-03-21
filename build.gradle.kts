plugins {
    kotlin("jvm") version "2.1.10"
    id("io.ktor.plugin") version "3.1.1"
    groovy // Spock
}

application {
    mainClass = "com.icosahedron.graphql.ApplicationKt"
}

group = "com.icosahedron"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-config-yaml")
    implementation("io.ktor:ktor-server-netty")
    implementation("io.ktor:ktor-server-status-pages")

    implementation("com.expediagroup:graphql-kotlin-ktor-server:8.4.0")
    implementation("com.google.protobuf:protobuf-java") {
        // Fix transitive vulnerability:
        // implementation("com.expediagroup:graphql-kotlin-ktor-server:8.4.0")
        // --> maven:com.google.protobuf:protobuf-java:4.27.1 is vulnerable
        version {
            strictly("4.28.2")
        }
    }

    implementation("ch.qos.logback:logback-classic:1.5.17")

    val spockVersion = "2.4-M5-groovy-4.0"
    testImplementation(platform("org.spockframework:spock-bom:$spockVersion"))
    testImplementation("org.spockframework:spock-core:$spockVersion")
}

tasks.test {
    useJUnitPlatform()

    testLogging {
        events("passed", "skipped", "failed")
    }
}

kotlin {
    jvmToolchain(19)

    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}