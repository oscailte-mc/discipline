enableFeaturePreview("VERSION_CATALOGS")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        maven("https://oss.sonatype.org/content/repositories/snapshots/")
        maven("https://repo.incendo.org/content/repositories/snapshots")
        // Paper API
        maven("https://papermc.io/repo/repository/maven-public/")
    }
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
}

pluginManagement {
    repositories {
        gradlePluginPortal()
    }
    includeBuild("build-logic")
}

plugins {
    id("ca.stellardrift.polyglot-version-catalogs") version "5.0.0"
}

rootProject.name = "discipline"

sequenceOf(
    "api",
    "common",
    "bukkit",
    "velocity"
).forEach {
    include("discipline-$it")
    project(":discipline-$it").projectDir = file(it)
}
