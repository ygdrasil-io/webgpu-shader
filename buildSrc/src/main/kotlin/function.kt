import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.TypeVariableName

context(context: CodeGeneratorTask)
internal fun FileSpec.Builder.generateInvocablesInterface(): FileSpec.Builder = apply {
    for (index in (0..context.maxParametersPerFunction)) {
        addType(generateInvocableInterface(index))
    }
}
context(context: CodeGeneratorTask)
internal fun FileSpec.Builder.generateInvocablesImplementation(): FileSpec.Builder = apply {
    for (index in (0..context.maxParametersPerFunction)) {
        addType(generateInvocableImplementation(index))
    }
}

context(context: CodeGeneratorTask)
fun generateInvocableInterface(parameters: Int): TypeSpec {
    // On suppose que "ShaderType" est déjà défini quelque part
    val shaderTypeClass = context.shaderTypeClass

    // Le type de retour générique T : ShaderType
    val returnTypeT = TypeVariableName("T", shaderTypeClass)

    // Génération des types génériques pour les paramètres (I1, I2, etc.)
    // Chaque Ix doit hériter de ShaderType
    val inputTypes = (1..parameters).map { i ->
        TypeVariableName("I$i", shaderTypeClass)
    }

    // Construction de la liste de tous les paramètres de type de l'interface <T, I1, I2...>
    val typeVariables = listOf(returnTypeT) + inputTypes

    // Construction de la fonction invoke(i1: I1, i2: I2...): T
    val invokeFunctionBuilder = FunSpec.builder("invoke")
        .addModifiers(KModifier.OPERATOR)
        .returns(returnTypeT)
        .addStatement("return defaultValue")

    // Ajout des paramètres à la fonction invoke
    inputTypes.forEachIndexed { index, typeVar ->
        invokeFunctionBuilder.addParameter("i${index + 1}", typeVar)
    }

    // Construction de l'interface
    return TypeSpec.interfaceBuilder("Invocable$parameters")
        .addTypeVariables(typeVariables)
        .addSuperinterface(shaderTypeClass)
        .addProperty(
            PropertySpec.builder("defaultValue", returnTypeT)
                .build()
        )
        .addFunction(invokeFunctionBuilder.build())
        .build()
}

context(context: CodeGeneratorTask)
fun generateInvocableImplementation(parameters: Int): TypeSpec {
    val shaderTypeClass = context.shaderTypeClass
    val returnTypeT = TypeVariableName("T", shaderTypeClass)

    // Génération des types génériques pour les paramètres (I1, I2, etc.)
    val inputTypes = (1..parameters).map { i ->
        TypeVariableName("I$i", shaderTypeClass)
    }

    val typeVariables = listOf(returnTypeT) + inputTypes

    // Récupération du nom de l'interface correspondante (ex: Invocable1)
    val interfaceClassName = ClassName(context.basePackageName, "Invocable$parameters")
    // Paramétrage de l'interface (ex: Invocable1<T, I1>)
    val superInterface = interfaceClassName.parameterizedBy(typeVariables)

    return TypeSpec.classBuilder("Invocable${parameters}Impl")
        .addModifiers(KModifier.INTERNAL)
        .addAnnotation(PublishedApi::class)
        .addTypeVariables(typeVariables)
        .addSuperinterface(superInterface)
        .primaryConstructor(
            FunSpec.constructorBuilder()
                .addParameter("defaultValue", returnTypeT)
                .build()
        )
        .addProperty(
            PropertySpec.builder("defaultValue", returnTypeT)
                .addModifiers(KModifier.OVERRIDE)
                .initializer("defaultValue")
                .build()
        )
        .addProperty(
            PropertySpec.builder("name", String::class)
                .addModifiers(KModifier.OVERRIDE)
                .initializer("defaultValue.name")
                .build()
        )
        .build()
}