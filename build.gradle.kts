import org.apache.tools.ant.filters.ReplaceTokens

plugins {
    id("java")
    id("java-library")
    id("com.github.johnrengelman.shadow") version ("8.1.1")
}

group = "live.webcrawls.profiles"
version = "1.0-SNAPSHOT"


java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
}

dependencies {
    // DX
    compileOnly("org.checkerframework:checker-qual:3.36.0")
    compileOnly("com.google.guava:guava:31.1-jre")

    // Minecraft
    compileOnly("io.papermc.paper:paper-api:1.20.1-R0.1-SNAPSHOT")
    compileOnly("me.clip:placeholderapi:2.11.3")
    implementation("net.kyori:adventure-text-minimessage:4.14.0")
    implementation("cloud.commandframework:cloud-paper:1.2.0")
    implementation("org.spongepowered:configurate-hocon:4.1.2")
}

tasks {
    build {
        dependsOn(shadowJar)
    }

    shadowJar {
        fun relocates(vararg dependencies: String) {
            dependencies.forEach {
                val split = it.split('.')
                val name = split.last();
                relocate(it, "${rootProject.group}.dependencies.$name")
            }
        }

        dependencies {
            exclude(dependency("com.google.guava:"))
            exclude(dependency("com.google.errorprone:"))
            exclude(dependency("org.checkerframework:"))
        }

        relocates(
                "com.typesafe.config",
                "com.zaxxer.hikari",
                "com.google.inject",
                "org.antlr",
                "org.slf4j",
                "org.jdbi",
                "org.aopalliance",
                "org.spongepowered.configurate",
                "io.leangen.geantyref",
                "cloud.commandframework",
                "net.kyori.adventure",
                "net.kyori.examination"
        )

        archiveFileName.set(project.name + ".jar")
        minimize()
    }

    processResources {
        filter<ReplaceTokens>("tokens" to mapOf("version" to project.version))
    }
}