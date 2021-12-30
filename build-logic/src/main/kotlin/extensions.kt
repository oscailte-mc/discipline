import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import net.kyori.indra.git.IndraGitExtension
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.the

/**
 * Relocate a package into the `net.draycia.carbon.libs` namespace.
 */
fun ShadowJar.relocateDependency(pkg: String) {
    relocate(pkg, "com.github.oscalitemc.discipline.libs.$pkg")
}

fun ShadowJar.relocateCloud() {
    relocateDependency("cloud.commandframework")
}

fun ShadowJar.configureShadowJar() {
    minimize()
}

fun Project.latestGitHash(): String? =
    the<IndraGitExtension>().commit()?.name?.substring(0, 7)

val Project.libs: LibrariesForLibs
    get() = the()
