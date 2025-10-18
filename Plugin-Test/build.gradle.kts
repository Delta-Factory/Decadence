plugins {
	id("java")
}

group = "delta.cion"
version = "0.0.0-DEV"

repositories {
	mavenCentral()
}

dependencies {
	compileOnly(project(":Decadence-API"))
}

tasks {
	build {
		dependsOn(shadowJar)
	}

	shadowJar {
		dependsOn(":Decadence-API:shadowJar")
		mergeServiceFiles()
		archiveClassifier.set("")
	}
}
