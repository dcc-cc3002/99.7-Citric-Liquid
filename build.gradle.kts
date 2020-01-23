plugins {
    java
}

group = "com.github.cc3002"
version = "0.1-RELEASE"

repositories {
    mavenCentral()
}

dependencies {
    // https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api
    testCompile(group = "org.junit.jupiter", name = "junit-jupiter-api", version = "5.6.0")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_11
}