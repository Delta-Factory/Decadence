plugins {
    id("java")
    kotlin("jvm") version "2.1.10"
    id("com.gradleup.shadow") version("8.3.0")
}

val minestomVersion: String by project

allprojects {
    apply(plugin = "java")
    apply(plugin = "com.gradleup.shadow")

    java {
        toolchain {
            languageVersion = JavaLanguageVersion.of(22)
        }
    }

    dependencies {
        compileOnly("net.minestom:minestom:$minestomVersion")
        compileOnly("org.yaml:snakeyaml:2.4")
        compileOnly("ch.qos.logback:logback-classic:1.5.18")
		implementation("com.google.code.gson:gson:2.13.1")
		implementation("org.xerial:sqlite-jdbc:3.42.0.0")
    }

    repositories {
        mavenCentral()
    }

	tasks.withType<JavaCompile> {
		options.encoding = "UTF-8"
		options.release = 21
	}
}
