plugins {
    id("discipline.base-conventions")
}

dependencies {
    api(projects.disciplineApi)
    api(libs.typesafeConfig)

    // Cloud
    api(platform(libs.cloudBom))
    api(libs.cloudCore)
    api(libs.cloudMinecraftExtras) {
        isTransitive = false
    }
}
