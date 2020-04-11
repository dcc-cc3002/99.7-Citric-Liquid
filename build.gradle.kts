import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

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
  testLogging {
    // set options for log level LIFECYCLE
    events(TestLogEvent.FAILED,
           TestLogEvent.PASSED,
           TestLogEvent.SKIPPED,
           TestLogEvent.STANDARD_OUT)
    exceptionFormat = TestExceptionFormat.SHORT

    showExceptions = true
    showCauses = true
    showStackTraces = true

    // set options for log level DEBUG and INFO
    debug {
      events(TestLogEvent.STARTED,
             TestLogEvent.FAILED,
             TestLogEvent.PASSED,
             TestLogEvent.SKIPPED,
             TestLogEvent.STANDARD_ERROR,
             TestLogEvent.STANDARD_OUT)
      exceptionFormat = TestExceptionFormat.FULL
    }
    info.events = debug.events
    info.exceptionFormat = debug.exceptionFormat

  }

  useJUnitPlatform()

  addTestListener(object : TestListener {
    override fun beforeSuite(suite: TestDescriptor) {}
    override fun beforeTest(testDescriptor: TestDescriptor) {}
    override fun afterTest(testDescriptor: TestDescriptor, result: TestResult) {}

    override fun afterSuite(suite: TestDescriptor, result: TestResult) {
      if (suite.parent == null) { // root suite
        logger.quiet("----")
        logger.quiet("Test result: ${result.resultType}")
        logger.quiet(
            "Test summary: ${result.testCount} tests, " +
                "${result.successfulTestCount} succeeded, " +
                "${result.failedTestCount} failed, " +
                "${result.skippedTestCount} skipped"
        )

      }
    }
  })
}

dependencies {
  implementation(group = "org.openjfx", name = "javafx", version = "14-ea+6", ext = "pom")
  implementation("org.jetbrains:annotations:19.0.0")
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

