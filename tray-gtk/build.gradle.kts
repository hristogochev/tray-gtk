plugins {
    kotlin("jvm")
    `maven-publish`
}

repositories {
}


dependencies {
    implementation("net.java.dev.jna:jna-jpms:5.12.1")
}

val sourcesJar by tasks.registering(Jar::class) {
    archiveClassifier.set("sources")
    from(sourceSets["main"].allSource)
}

val javadocJar by tasks.registering(Jar::class) {
    archiveClassifier.set("javadoc")
}


publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.hristogochev"
            artifactId = "tray-gtk"
            version = "0.2.2"

            from(components["java"])
            artifact(sourcesJar) {
                classifier = "sources"
            }
            artifact(javadocJar) {
                classifier = "javadoc"
            }
        }
    }
}






