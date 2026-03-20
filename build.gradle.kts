plugins {
    `java-library`
    `maven-publish`
    `jacoco`
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
    testImplementation("org.junit.jupiter:junit-jupiter:5.7.1")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

jacoco {
    toolVersion = "0.8.14"
    reportsDirectory.set(layout.buildDirectory.dir("customJacocoReportDir"))
}

tasks.named<Test>("test") {
    useJUnitPlatform()
    maxHeapSize = "1G"
    testLogging {
        events("passed")
    }
    finalizedBy(tasks.jacocoTestReport)
}

tasks.named<JacocoReport>("jacocoTestReport") {
    dependsOn(tasks.test)
    reports {
        xml.required.set(false)
        csv.required.set(false)
        html.outputLocation.set(layout.buildDirectory.dir("jacocoHtml"))
    }
    finalizedBy(tasks.named("openJacocoReport"))
}

tasks.register("openJacocoReport") {
    dependsOn(tasks.named("jacocoTestReport"))
    doLast {
        val reportFile = layout.buildDirectory.dir("jacocoHtml").get().file("index.html").asFile
        if (reportFile.exists()) {
            val os = System.getProperty("os.name").toLowerCase()
            when {
                os.contains("win")   -> ProcessBuilder("cmd", "/c", "start", reportFile.absolutePath)
                os.contains("mac")   -> ProcessBuilder("open", reportFile.absolutePath)
                else                 -> ProcessBuilder("xdg-open", reportFile.absolutePath)
            }.start()
        } else {
            logger.warn("JaCoCo report not found at ${reportFile.absolutePath}")
        }
    }
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
                username = findProperty("gpr.user") as String? ?: System.getenv("GITHUB_ACTOR")
                password = findProperty("gpr.key") as String? ?: System.getenv("GITHUB_TOKEN")
            }
        }
    }
}