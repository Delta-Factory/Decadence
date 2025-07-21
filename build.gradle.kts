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
            languageVersion = JavaLanguageVersion.of(21)
        }
    }

    dependencies {
        compileOnly("net.minestom:minestom-snapshots:$minestomVersion")
        compileOnly("org.yaml:snakeyaml:2.4")
        compileOnly("ch.qos.logback:logback-classic:1.5.18")
        compileOnly("com.mysql:mysql-connector-j:9.2.0")
        compileOnly("com.h2database:h2:2.3.232")
    }

    repositories {
        mavenCentral()
    }

	tasks.withType<JavaCompile> {
		options.encoding = "UTF-8"
	}
}
