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
	Author: @nionim (DeltaCion)
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
	implementation("net.minestom:minestom-snapshots:$minestomVersion")
	implementation("org.yaml:snakeyaml:2.4")
	implementation("ch.qos.logback:logback-classic:1.5.18")
	implementation("com.mysql:mysql-connector-j:9.2.0")
	implementation("com.h2database:h2:2.3.232")
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
			artifactId = "topaz_api"
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
