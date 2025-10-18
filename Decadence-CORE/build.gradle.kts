plugins {
	id("java")
}

val serverVersion: String by project

group = "decadence"
version = serverVersion

repositories {
	mavenCentral()
}

dependencies {
	implementation(project(":Decadence-API"))
}

tasks {

	jar {
		manifest {
			attributes["Main-Class"] = "decadence.server.Decadence"
		}
	}

	build {
		dependsOn(shadowJar)
	}

	shadowJar {
		dependsOn(":Decadence-API:shadowJar")
		mergeServiceFiles()
		archiveClassifier.set("")
	}
}
