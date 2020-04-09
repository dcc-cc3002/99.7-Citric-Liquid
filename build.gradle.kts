plugins {
  java
  id("application")
  id("org.openjfx.javafxplugin") version "0.0.8"
}

application {
  mainClassName = "$moduleName/com.github.cc3002.citricliquid.gui.JavaFXHelloWorld"
}

group = "com.github.cc3002"
version = "1.0-RELEASE"

repositories {
  mavenCentral()
}

tasks.named<Test>("test") {
  useJUnitPlatform()
}

dependencies {
  implementation(group = "org.openjfx", name = "javafx", version = "14-ea+6", ext = "pom")
  testImplementation(group = "org.junit.jupiter", name = "junit-jupiter-api",
          version = "5.1.0")
  testRuntimeOnly(group = "org.junit.jupiter", name = "junit-jupiter-engine",
          version = "5.1.0")
}

javafx {
  version = "14-ea+6"
  modules = mutableListOf("javafx.controls")
}
configure<JavaPluginConvention> {
  sourceCompatibility = JavaVersion.VERSION_11
}

