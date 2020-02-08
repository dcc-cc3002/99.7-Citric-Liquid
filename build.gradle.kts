plugins {
  java
  id("application")
  id("org.openjfx.javafxplugin") version "0.0.8"
}

group = "com.github.cc3002"
version = "1.0-DEV"

repositories {
  mavenCentral()
}

dependencies {
  implementation(group = "org.openjfx", name = "javafx", version = "14-ea+6", ext = "pom")
  // https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api
  testImplementation(group = "org.junit.jupiter", name = "junit-jupiter-api",
                     version = "5.6.0")
}

javafx {
  version = "14-ea+6"
  modules = mutableListOf("javafx.controls")
}

configure<JavaPluginConvention> {
  sourceCompatibility = JavaVersion.VERSION_11
}

tasks {
  test {
    useJUnitPlatform()
  }
}