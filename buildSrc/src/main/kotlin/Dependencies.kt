object Dependencies {
    // Kotlin stdlib
    object Kotlin {
        const val STDLIB = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.KOTLIN}"
    }

    // Flyway database migrations
    object Flyway {
        const val CORE = "org.flywaydb:flyway-core:${Versions.FLYWAY}"
    }

    // Logging
    object Logging {
        const val LOGBACK_CLASSIC = "ch.qos.logback:logback-classic:${Versions.LOGBACK}"
    }

    // Ktor
    object Ktor {
        const val SERVER_CORE = "io.ktor:ktor-server-core:${Versions.KTOR}"
        const val SERVER_TEST_HOST = "io.ktor:ktor-server-test-host:${Versions.KTOR}"
    }

    // Publishing
    object PublishPlugin {
        const val SIGNING_ID = "signing"
        const val MAVEN_PUBLISH_ID = "com.vanniktech.maven.publish"
        const val MAVEN_PUBLISH_VERSION = Versions.MAVEN_PUBLISH
    }


    // Testcontainers
    object TestContainers {
        const val CORE = "org.testcontainers:testcontainers:${Versions.TEST_CONTAINERS}"
        const val POSTGRESQL = "org.testcontainers:postgresql:${Versions.TEST_CONTAINERS}"
        const val JUNIT_JUPITER = "org.testcontainers:junit-jupiter:${Versions.TEST_CONTAINERS}"
    }

    // Databases / Pools
    object Database {
        const val HIKARI_CP = "com.zaxxer:HikariCP:${Versions.HIKARI}"
        const val POSTGRESQL = "org.postgresql:postgresql:${Versions.POSTGRESQL}"
    }

    // JUnit
    object JUnit {
        const val BOM = "org.junit:junit-bom:${Versions.JUNIT}"
        const val JUPITER_API = "org.junit.jupiter:junit-jupiter-api"
        const val JUPITER_PARAMS = "org.junit.jupiter:junit-jupiter-params"
        const val JUPITER_ENGINE = "org.junit.jupiter:junit-jupiter-engine"
        const val JUPITER_PLATFORM = "org.junit.platform:junit-platform-launcher"
    }
}
