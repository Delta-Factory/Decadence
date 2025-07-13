plugins {
	id("java")
}

val serverVersion: String by project

group = "delta.cion"
version = serverVersion

repositories {
	mavenCentral()
}

dependencies {
	implementation(project(":Topaz-API"))
}

tasks {

	jar {
		manifest {
			attributes["Main-Class"] = "delta.cion.server.Topaz"
		}
	}

	build {
		dependsOn(shadowJar)
	}

	shadowJar {
		dependsOn(":Topaz-API:shadowJar")
		mergeServiceFiles()
		archiveClassifier.set("")
	}
}
