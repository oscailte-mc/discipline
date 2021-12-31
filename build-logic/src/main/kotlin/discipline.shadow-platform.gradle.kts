plugins {
    id("discipline.platform-conventions")
    id("com.github.johnrengelman.shadow")
}

val shadowPlatform = extensions.create<DisciplineShadowPlatformExtension>("disciplineShadowPlatform", project)

tasks {
    jar {
        archiveClassifier.set("unshaded")
    }
    shadowJar {
        archiveClassifier.set(null as String?)
        configureShadowJar()
    }
}

extensions.configure<DisciplinePlatformExtension> {
    jarTask.set(tasks.shadowJar)
}

afterEvaluate {
    tasks.shadowJar {
        if (shadowPlatform.relocateCloud.get()) {
            relocateCloud()
        }
    }
}
