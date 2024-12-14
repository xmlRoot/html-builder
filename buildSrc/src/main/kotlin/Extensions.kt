import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

internal fun DependencyHandler.targetLib(lib : String, subProject : String) = project(":libs:$lib:$subProject")


const val inputSources = "input-sources"
const val htmlTarget = "html-target"
const val htmlBuilder = "html-builder"

fun DependencyHandler.core(lib : String) = targetLib(lib, "core")
fun DependencyHandler.config(lib : String) = targetLib(lib, "config")
fun DependencyHandler.driver(lib : String) = targetLib(lib, "driver")
fun DependencyHandler.output(lib : String) = targetLib(lib, "output")

fun DependencyHandler.common() = project(":libs:common")
