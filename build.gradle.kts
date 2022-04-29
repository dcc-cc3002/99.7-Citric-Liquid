@file:Suppress("SpellCheckingInspection")

val junitVersion: String by project
val controlsfxVersion: String by project
val annotationVersion: String by project

plugins {
  java
  application
  id("org.openjfx.javafxplugin") version "0.0.10"
  id("org.beryx.jlink") version "2.24.1"
}


val group = "cl.uchile.dcc"
val version = "1.1.222804"

application {
  mainClass.set("cl.uchile.dcc.citricliquid.view.CitricLiquidApp")
}

javafx {
  version = "18-ea+6"
  modules = mutableListOf("javafx.controls", "javafx.fxml")
}

repositories {
  mavenCentral()
}

dependencies {
  implementation(group = "org.openjfx", name = "javafx", version = "18.0.1")
  implementation(group = "org.jetbrains", name = "annotations", version = annotationVersion)
  implementation(group = "org.controlsfx", name = "controlsfx", version = controlsfxVersion)
  testImplementation(
    group = "org.junit.jupiter", name = "junit-jupiter-api", version = junitVersion
  )
  testRuntimeOnly(
    group = "org.junit.jupiter",
    name = "junit-jupiter-engine",
    version = junitVersion
  )
}

tasks.withType(JavaCompile::class.java) {
  options.encoding = "UTF-8"
  options.isFork = true
  options.isIncremental = true
  options.isDebug = true
//  options.isVerbose = true
}

tasks.withType<Test> {
  useJUnitPlatform()
}

jlink {
    imageZip.set(project.file("${buildDir}/distributions/app-${javafx.platform.classifier}.zip"))
    options.set(
        mutableListOf(
            "--strip-debug",
            "--compress",
            "2",
            "--no-header-files",
            "--no-man-pages"
        )
    )
    launcher {
        name = "app"
    }
}

tasks.withType<org.beryx.jlink.JlinkZipTask> {
    group = "distribution"
}