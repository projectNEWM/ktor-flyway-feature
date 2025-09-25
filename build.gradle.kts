import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("java-library")
    id("com.github.ben-manes.versions") version Versions.VERSIONS
    id("com.adarshr.test-logger") version Versions.TEST_LOGGER
    id("org.jetbrains.kotlin.jvm") version Versions.KOTLIN
    id(Dependencies.PublishPlugin.MAVEN_PUBLISH_ID) version Dependencies.PublishPlugin.MAVEN_PUBLISH_VERSION
    id(Dependencies.PublishPlugin.SIGNING_ID)
}

group = "io.newm"
version = "3.3.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(Dependencies.Flyway.CORE)
    implementation(Dependencies.Kotlin.STDLIB)

    runtimeOnly(Dependencies.Logging.LOGBACK_CLASSIC)

    implementation(Dependencies.Ktor.SERVER_CORE)

    testImplementation(Dependencies.Ktor.SERVER_TEST_HOST)
    testImplementation(Dependencies.TestContainers.POSTGRESQL)
    testImplementation(Dependencies.TestContainers.CORE)
    testImplementation(Dependencies.TestContainers.JUNIT_JUPITER)
    testImplementation(Dependencies.Database.HIKARI_CP)
    testImplementation(Dependencies.Database.POSTGRESQL)
    testImplementation(platform(Dependencies.JUnit.BOM))
    testImplementation(Dependencies.JUnit.JUPITER_API)
    testImplementation(Dependencies.JUnit.JUPITER_PARAMS)
    testRuntimeOnly(Dependencies.JUnit.JUPITER_ENGINE)
    testRuntimeOnly(Dependencies.JUnit.JUPITER_PLATFORM)
}


project.tasks.withType<org.jetbrains.kotlin.gradle.tasks.UsesKotlinJavaToolchain>().configureEach {
    val service = project.extensions.getByType<JavaToolchainService>()
    val customLauncher =
        service.launcherFor {
            this.languageVersion.set(JavaLanguageVersion.of(JavaVersion.VERSION_21.majorVersion))
        }

    this.kotlinJavaToolchain.toolchain.use(customLauncher)
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    compilerOptions {
        freeCompilerArgs =
            listOf(
                "-Xjsr305=strict",
                "-opt-in=kotlin.RequiresOptIn",
            )
        jvmTarget.set(JvmTarget.JVM_21)
    }
}

tasks.withType<DependencyUpdatesTask> {
    rejectVersionIf {
        isNonStable(candidate.version) && !isNonStable(currentVersion)
    }
}

fun isNonStable(version: String): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.uppercase().contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(version)
    return isStable.not()
}

tasks.withType<Test> {
    useJUnitPlatform()
}

signing {
    useGpgCmd()
}

mavenPublishing {
    publishToMavenCentral()
    signAllPublications()
    coordinates("io.newm", "ktor-flyway-feature", version.toString())
    pom {
        name.set("ktor-flyway-feature")
        description.set("Ktor Flyway feature")
        url.set("https://github.com/projectNEWM/newm-server")
        licenses {
            license {
                name.set("MIT")
                url.set("https://raw.githubusercontent.com/projectNEWM/ktor-flyway-feature/master/LICENSE")
            }
        }
        developers {
            developer {
                id.set("AndrewWestberg")
                name.set("Andrew Westberg")
                email.set("andrewwestberg@gmail.com")
                organization.set("NEWM")
                organizationUrl.set("https://newm.io")
            }
        }
        scm {
            connection.set("scm:git:git://github.com/projectNEWM/ktor-flyway-feature.git")
            developerConnection.set("scm:git:ssh://github.com/projectNEWM/ktor-flyway-feature.git")
            url.set("https://github.com/projectNEWM/ktor-flyway-feature")
        }
    }
}