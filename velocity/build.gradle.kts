plugins {
    id("discipline.shadow-platform")
}

dependencies {
    implementation(projects.disciplineCommon)

    compileOnly(libs.velocityApi)
    annotationProcessor(libs.velocityApi)

    implementation(libs.cloudVelocity)
}
