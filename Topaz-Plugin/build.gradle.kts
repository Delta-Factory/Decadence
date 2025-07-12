plugins {
	id("java")
}

group = "delta.cion"
version = "0.0.0-DEV"

repositories {
	mavenCentral()
}

dependencies {
	compileOnly(project(":Topaz-API"))
}

tasks {
	build {
		dependsOn(shadowJar)
	}

	shadowJar {
		dependsOn(":Topaz-API:shadowJar")
		mergeServiceFiles()
		archiveClassifier.set("")
	}
}
