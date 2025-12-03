import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.TypeVariableName
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
    val maxParametersPerFunction = 20
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

        FileSpec.builder("experiment.redo", "GeneratedCode")
            .generateInvocablesInterface()
            .generateInvocablesImplementation()
        .build().toString()
            .let { "/* This is a generated file. Do not edit! */\n\n$it" }
            .also { outputDir.resolve("function.generated.kt").writeText(it) }

    }
}
