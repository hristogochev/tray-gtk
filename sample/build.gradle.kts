
plugins {
    kotlin("jvm")
    application
}

dependencies {
    implementation(project(mapOf("path" to ":tray-gtk")))
}

application {
    mainClass.set("MainKt")
}
