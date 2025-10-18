plugins {
	id("java-library")
	id("maven-publish")
}

val apiVersion: String by project
val minestomVersion: String by project

val description: String by project

// Not work on this time
val publishToLocal: Boolean = true

val m = """
	======================================
	Description: %s
	Author: @nionim
	For: Project~Violette
	Support: https://discord.gg/MEBkvJbe4P
	======================================
	Minestom Version: %s
	String to Import API:
	%s:%s:%s
	======================================
	"""

group = "delta.cion.api"
version = apiVersion

dependencies {
	implementation("net.minestom:minestom:$minestomVersion")
	implementation("org.yaml:snakeyaml:2.4")
	implementation("ch.qos.logback:logback-classic:1.5.18")
	implementation("org.xerial:sqlite-jdbc:3.42.0.0")
}

tasks {

	build {
		dependsOn(shadowJar)
		dependsOn("publishToMavenLocal")
	}

	shadowJar {
		mergeServiceFiles()
		archiveClassifier.set("")
	}

	register<Jar>("javadocJar") {
		dependsOn(javadoc)
		archiveClassifier.set("javadoc")
		from(javadoc.get().destinationDir)
	}

	register<Jar>("sourcesJar") {
		archiveClassifier.set("sources")
		from(sourceSets["main"].allSource)
	}
}

publishing {
	publications {
		create<MavenPublication>("mavenJava") {
			from(components["java"])
			groupId = group.toString()
			artifactId = "decadence_api"
			version = version

			println(String.format(m, description, minestomVersion, groupId, artifactId, version))

			artifact(tasks["javadocJar"])
			artifact(tasks["sourcesJar"])
		}
	}
	repositories { mavenLocal() }
}

artifacts {
	add("archives", tasks["javadocJar"])
	add("archives", tasks["sourcesJar"])
}
