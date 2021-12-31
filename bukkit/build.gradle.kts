plugins {
    id("discipline.shadow-platform")
}

dependencies {
    implementation(projects.disciplineCommon)

    compileOnly(libs.paperApi)
    implementation(libs.paperLib)

    // Commands
    implementation(libs.cloudPaper)
}


