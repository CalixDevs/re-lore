plugins {
    kotlin("jvm") version "1.3.72"

    id("com.github.johnrengelman.shadow") version "5.2.0"
}

group = "io.arct"
version = "1.0.0"

repositories {
    mavenCentral()

    maven(url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/"))

    maven(url = uri("https://oss.sonatype.org/content/repositories/snapshots"))
    maven(url = uri("https://oss.sonatype.org/content/repositories/central"))
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.spigotmc:spigot-api:1.8.8-R0.1-SNAPSHOT")
}

sourceSets {
    main {
        java {
            srcDirs("kotlin")
        }
    }

    test {
        java {
            srcDirs()
        }
    }
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}

configurations {
    runtime {
        exclude(module = "spigot-api")
    }
}

tasks {
    shadowJar {
        archiveBaseName.set("relore")
        archiveClassifier.set("")
        archiveVersion.set("1.0.0")

        minimize()
    }
}