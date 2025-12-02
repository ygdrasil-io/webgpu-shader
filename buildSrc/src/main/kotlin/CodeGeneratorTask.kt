import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import org.gradle.api.DefaultTask
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import java.io.File

open class CodeGeneratorTask : DefaultTask() {

    @OutputDirectory
    val outputDirectory: DirectoryProperty = project.objects.directoryProperty()

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
            .addType(
                TypeSpec.objectBuilder("GeneratedCode")
                    .addProperty(
                        PropertySpec.builder("TIMESTAMP", String::class)
                            .initializer("%S", System.currentTimeMillis().toString())
                            .build()
                    )
                    .addFunction(
                        FunSpec.builder("hello")
                            .returns(String::class)
                            .addStatement("return %S", "Hello from generated code!")
                            .build()
                    )
                    .addKdoc("Code généré automatiquement avec KotlinPoet")
                    .build()
            )
            .build()
            .also { outputDir.resolve("function.generated.kt").writeText(it.toString()) }

    }
}
