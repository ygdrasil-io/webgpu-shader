import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import org.gradle.api.DefaultTask
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import java.io.File

open class CodeGeneratorTask : DefaultTask() {

    @OutputDirectory
    val outputDirectory: DirectoryProperty = project.objects.directoryProperty()

    @Internal
    val maxParametersPerFunction = 10
    @Internal
    val basePackageName = "experiment.redo"
    @Internal
    val shaderTypeClass = ClassName("experiment.redo", "ShaderType")

    init {
        group = "generator"
        outputDirectory.convention(
            project.layout.projectDirectory
                .dir("shared")
                .dir("src")
                .dir("commonMain")
                .dir("kotlin")
        )
    }

    @TaskAction
    fun generate() {
        generate(outputDirectory.get().asFile)
    }

    fun generate(outputDir: File) {
        outputDir.mkdirs()

        generateCodeFile(outputDir, "function.invocable") {
            generateInvocablesInterface()
            .generateInvocablesImplementation()

        }

        generateCodeFile(outputDir, "function.extension") {
            generateFnExtensionsFunction()
        }

        generateCodeFile(outputDir, "function.statement") {
            generateFunctionsStatementClass()
        }

    }

    private fun generateCodeFile(outputDir: File, fileNamePrefix: String, action: FileSpec.Builder.() -> Unit = {}) {
        FileSpec.builder(basePackageName, "N/A")
            .apply { action() }
            .build().toString()
            .let { "/* This is a generated file. Do not edit! */\n\n$it" }
            .also { outputDir.resolve("${fileNamePrefix}.generated.kt").writeText(it) }
    }
}
