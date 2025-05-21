plugins {
    java
    groovy
}

group = "org.sportradar.examplescoreboard"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.spockframework:spock-core:2.3-groovy-4.0")

    // Groovy for Spock
    testImplementation("org.apache.groovy:groovy-all:4.0.15")
}

tasks.test {
    useJUnitPlatform()
}