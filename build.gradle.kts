plugins {
    kotlin("jvm") version "2.1.10"
    groovy // Spock
}

group = "com.icosahedron"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

//    implementation("com.google.protobuf:protobuf-java") {
//        version {
//            strictly("4.28.2")
//        }
//    }

    implementation("com.expediagroup:graphql-kotlin-ktor-server:8.4.0")

    val ktorVersion = "3.0.3"
    implementation("io.ktor:ktor-server-config-yaml:$ktorVersion")
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("io.ktor:ktor-server-status-pages:$ktorVersion")

//    implementation("io.ktor:ktor-server-host-common:$ktorVersion")
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
}