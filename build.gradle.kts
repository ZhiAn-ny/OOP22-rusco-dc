plugins {
    // Apply the java plugin to add support for Java
    java

    // Apply the application plugin to add support for building a CLI application
    // You can run your app via task "run": ./gradlew run
    application

    /*
     * Adds tasks to export a runnable jar.
     * In order to create it, launch the "shadowJar" task.
     * The runnable jar will be found in build/libs/projectname-all.jar
     */
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("org.danilopianini.gradle-java-qa") version "0.43.0"

    id("org.openjfx.javafxplugin") version "0.0.13"
    id("org.beryx.jlink") version "2.24.1"
}

repositories {
    mavenCentral()
}

dependencies {
    // Suppressions for SpotBugs
    compileOnly("com.github.spotbugs:spotbugs-annotations:4.7.3")
    // JUnit API and testing engine
    val jUnitVersion = "5.7.1"
    testImplementation("org.junit.jupiter:junit-jupiter-api:$jUnitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$jUnitVersion")

    // https://mavenlibs.com/maven/dependency/org.openjfx/javafx-base
    val supportedPlatforms = listOf("linux", "mac", "win") // All required for OOP
    val javaFxVersion = "17.0.1"
    val javaFXModules = listOf(
        "base",
        "controls",
        "fxml",
        "swing",
        "graphics"
    )
    // JavaFX: comment out if you do not need them
    for (platform in supportedPlatforms) {
        for (module in javaFXModules) {
            implementation("org.openjfx:javafx-$module:$javaFxVersion:$platform")
        }
    }
}

application {
    // Define the main class for the application
    mainClass.set("it.unibo.ruscodc.launcher.Launcher")
}
