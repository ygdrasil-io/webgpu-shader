import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import java.io.File

open class CodeGeneratorTask : DefaultTask() {

    init {
        group = "generator"
    }

    @TaskAction
    fun generate() {
        generate(
            project.projectDir
                .resolve("shared")
                .resolve("src")
                .resolve("commonMain")
                .resolve("kotlin")
        )
    }

    fun generate(outputDir: File) {
        outputDir.mkdirs()

        val fileSpec = FileSpec.builder("generated", "GeneratedCode")
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

        fileSpec.writeTo(outputDir)
    }
}
