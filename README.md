# Ktor flyway feature

[ ![Download](https://api.bintray.com/packages/viartemev/Maven/ktor-flyway-feature/images/download.svg) ](https://bintray.com/viartemev/Maven/ktor-flyway-feature/_latestVersion)
## Usage
Set up a repository:
```groovy
repositories {
    maven {
        url  "https://dl.bintray.com/viartemev/Maven" 	
    }
}
dependencies {
    implementation("com.viartemev:ktor-flyway-feature:$ktor_flyway_feature_version")
}
```
Add the feature to the code:
```kotlin
install(FlywayFeature) {
    dataSource = database.connectionPool //required
    location = "custom/dir" //optional, default value = "db/migration"
    commands = listOf(Migrate) //optional, default value = listOf(Info, Migrate)
}
```
