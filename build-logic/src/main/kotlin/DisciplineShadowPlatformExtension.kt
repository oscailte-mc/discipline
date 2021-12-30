import org.gradle.api.Project
import org.gradle.api.provider.Property
import org.gradle.kotlin.dsl.property

open class DisciplineShadowPlatformExtension(project: Project) {
    val relocateCloud: Property<Boolean> = project.objects.property<Boolean>().convention(true)
}
