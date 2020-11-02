plugins {
    java
    application
}

group = "org.ramanova"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    val vertxVersion = "3.7.0"
    implementation("io.vertx:vertx-web:${vertxVersion}")
}

application {
    mainClassName = "server_app.main.BackendVerticle"
}
