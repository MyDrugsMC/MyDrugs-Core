plugins {
    `java-library`
    `maven-publish`
}

group = property("group") as String
version = property("version") as String

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
    withSourcesJar()
    withJavadocJar()
}

repositories {
    mavenCentral()
}

dependencies {
    // Put dependencies that are part of your public API here:
    // api("group:artifact:version")

    // Put internal implementation deps here:
    // implementation("group:artifact:version")
}

publishing {
    publications {
        create<MavenPublication>("gpr") {
            from(components["java"])

            artifactId = "mydrugs-core"

            pom {
                name.set("mydrugs-core")
                description.set("A reusable Gradle library published from GitHub.")
                url.set("https://github.com/MyDrugsMC/MyDrugs-Core")

                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }

                developers {
                    developer {
                        id.set("MyDrugsMC")
                        name.set("MyDrugsMC")
                    }
                }

                scm {
                    url.set("https://github.com/MyDrugsMC/MyDrugs-Core")
                    connection.set("scm:git:https://github.com/MyDrugsMC/MyDrugs-Core.git")
                    developerConnection.set("scm:git:ssh://git@github.com/MyDrugsMC/MyDrugs-Core.git")
                }
            }
        }
    }

    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/mydrugsmc/mydrugs-core")
            credentials {
                username = findProperty("gpr.user") as String?
                    ?: System.getenv("GITHUB_ACTOR")
                password = findProperty("gpr.key") as String?
                    ?: System.getenv("GITHUB_TOKEN")
            }
        }
    }
}