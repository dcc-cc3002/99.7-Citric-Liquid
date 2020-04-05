plugins {
  java
}

group = "com.github.cc3002"
version = "1.0-RELEASE"

repositories {
  mavenCentral()
}

dependencies {
  // https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api
  testImplementation(group = "org.junit.jupiter", name = "junit-jupiter-api",
                     version = "5.6.0")
}

configure<JavaPluginConvention> {
  sourceCompatibility = JavaVersion.VERSION_11
}

tasks {
  test {
    useJUnitPlatform()
  }
}