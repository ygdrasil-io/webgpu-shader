@file:OptIn(ExperimentalKotlinPoetApi::class)

import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.ExperimentalKotlinPoetApi
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.LambdaTypeName
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.TypeVariableName
import com.squareup.kotlinpoet.WildcardTypeName
import com.squareup.kotlinpoet.asClassName

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
internal fun FileSpec.Builder.generateFnExtensionsFunction(): FileSpec.Builder = apply {
    for (index in (0..context.maxParametersPerFunction)) {
        addFunction(generateFnExtensionFunction(index))
    }
}

context(context: CodeGeneratorTask)
internal fun FileSpec.Builder.generateFunctionsStatementClass(): FileSpec.Builder = apply {
    for (index in (0..context.maxParametersPerFunction)) {
        addType(generateFunctionStatementClass(index))
    }
}

context(context: CodeGeneratorTask)
private fun generateInvocableInterface(parameters: Int): TypeSpec {
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
private fun generateInvocableImplementation(parameters: Int): TypeSpec {
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

    // List<ShaderType>
    val listShaderType = List::class.asClassName().parameterizedBy(shaderTypeClass)

    return TypeSpec.classBuilder("Invocable${parameters}Impl")
        .addModifiers(KModifier.INTERNAL)
        .addAnnotation(PublishedApi::class)
        .addTypeVariables(typeVariables)
        .addSuperinterface(superInterface)
        .primaryConstructor(
            FunSpec.constructorBuilder()
                .addParameter("defaultValue", returnTypeT)
                .addParameter("parameters", listShaderType)
                .build()
        )
        .addProperty(
            PropertySpec.builder("defaultValue", returnTypeT)
                .addModifiers(KModifier.OVERRIDE)
                .initializer("defaultValue")
                .build()
        )
        .addProperty(
            PropertySpec.builder("parameters", listShaderType)
                .addModifiers(KModifier.PRIVATE)
                .initializer("parameters")
                .build()
        )
        .addProperty(
            PropertySpec.builder("name", String::class)
                .addModifiers(KModifier.OVERRIDE)
                .initializer("defaultValue.name")
                .build()
        )
        .addProperty(
            PropertySpec
                .builder(
                    "functionProperty",
                    context.readOnlyPropertyBaseStatementClass
                        .parameterizedBy(superInterface)
                        .copy(nullable = true)
                )
                .mutable(true)
                .initializer("null")
                .build()
        )
        .addFunction(
            FunSpec.builder("invoke")
                .contextParameter("context", context.shaderBuilderScopeClass)
                .addModifiers(KModifier.OPERATOR)
                .addParameters((1..parameters).map { i ->
                    ParameterSpec("I$i", inputTypes[i - 1])
                })
                .returns(returnTypeT)
                .addCode(
                    CodeBlock.builder()
                        .addStatement("val functionName = functionProperty?.propertyName ?: error(\"fail to get function name\")")
                        .addStatement("invoqueStatement(functionName, parameters)")
                        .addStatement("return defaultValue")
                    .build()
                )
                .build()
        )
        .build()
}

context(context: CodeGeneratorTask)
private fun generateFnExtensionFunction(parameters: Int): FunSpec {
    val basePackage = context.basePackageName
    val shaderTypeClass = context.shaderTypeClass

    // Définition des classes utilisées dans le corps de la fonction
    val shaderBuilderScopeClass = context.shaderBuilderScopeClass
    val shaderFunctionBuilderScopeClass = ClassName(basePackage, "ShaderFunctionBuilderScope")
    val readOnlyPropertyClass = ClassName(basePackage, "ReadOnlyPropertyBaseStatement")
    val functionStatementClass = ClassName(basePackage, "FunctionStatement$parameters")
    val invocableImplClass = ClassName(basePackage, "Invocable${parameters}Impl")
    val invocableInterfaceClass = ClassName(basePackage, "Invocable$parameters")
    val implScopeClass = ClassName(basePackage, "ShaderFunctionBuilderScopeImpl")
    val endBlockClass = ClassName(basePackage, "EndBlockStatement")

    // Génériques: T, I1, I2... (tous reified)
    val typeT = TypeVariableName("T", shaderTypeClass).copy(reified = true)
    val inputTypes = (1..parameters).map {
        TypeVariableName("I$it", shaderTypeClass).copy(reified = true)
    }
    val allTypeVariables = listOf(typeT) + inputTypes

    // Type de retour: ReadOnlyPropertyBaseStatement<InvocableN<T, I1...>>
    val returnType = readOnlyPropertyClass.parameterizedBy(
        invocableInterfaceClass.parameterizedBy(allTypeVariables)
    )

    // Paramètre block: ShaderFunctionBuilderScope.() -> Unit
    val blockType = LambdaTypeName.get(
        receiver = shaderFunctionBuilderScopeClass,
        returnType = Unit::class.asClassName()
    )

    return FunSpec.builder("fn")
        .contextParameter("scope", shaderBuilderScopeClass)
        .addAnnotation(
            AnnotationSpec.builder(JvmName::class)
                .addMember("%S", "fn$parameters")
                .build()
        )
        .addModifiers(KModifier.INLINE)
        .addTypeVariables(allTypeVariables)
        .addParameter("block", blockType)
        .returns(returnType)
        .addCode(
            CodeBlock.builder().apply {
                // 1. Récupération des valeurs par défaut
                addStatement("val defaultValue = getDefaultValue<%T>()", typeT)
                inputTypes.forEachIndexed { i, type ->
                    addStatement("val i${i + 1} = getDefaultValue<%T>()", type)
                }
                add("\n")
                if (parameters == 0) {
                    addStatement("val invocable = ${invocableImplClass.simpleName}<T>(defaultValue, emptyList())")
                } else {
                    addStatement("val invocable = ${invocableImplClass.simpleName}<T, ${
                        (1..parameters).joinToString(", ") { "I$it" }
                    }>(defaultValue, listOf(${
                        (1..parameters).joinToString(", ") { "i$it" }
                    }))")
                }

                // 2. Création de la liste d'arguments
                val argsList = if (parameters == 0) "emptyList()" else "listOf(" + (1..parameters).joinToString(", ") { "i$it" } + ")"

                // 3. Instanciation du Statement
                add("val statement = %T(\n", functionStatementClass)
                indent()
                add("scope,\n")
                indent()
                add("invocable,\n")
                add("$argsList\n")
                unindent()
                addStatement(").addToScope()")
                add("\n")

                // 4. Exécution du block et fermeture
                addStatement("%T(scope, statement).block()", implScopeClass)
                add("\n")
                addStatement("// Add end block statement to stack")
                addStatement("%T(scope).addToScope()", endBlockClass)
                .addStatement("invocable.functionProperty = statement")
                addStatement("return statement")
            }.build()
        )
        .build()
}

context(context: CodeGeneratorTask)
fun generateFunctionStatementClass(parameters: Int): TypeSpec {
    val basePackage = context.basePackageName
    val shaderTypeClass = context.shaderTypeClass

    // Types utilisés
    val shaderBuilderScopeClass = ClassName(basePackage, "ShaderBuilderScope")
    val functionInputClass = ClassName(basePackage, "FunctionInput")
    val functionWithParametersClass = ClassName(basePackage, "FunctionWithParameters")
    val readOnlyPropertyClass = ClassName(basePackage, "ReadOnlyPropertyBaseStatement")
    val invocableInterfaceClass = ClassName(basePackage, "Invocable$parameters")

    // Génériques T, I1...
    val typeT = TypeVariableName("T", shaderTypeClass)
    val inputTypes = (1..parameters).map { TypeVariableName("I$it", shaderTypeClass) }
    val allTypeVariables = listOf(typeT) + inputTypes

    // InvocableN<T, I1...>
    val invocableType = invocableInterfaceClass.parameterizedBy(allTypeVariables)

    // List<ShaderType>
    val listShaderType = List::class.asClassName().parameterizedBy(shaderTypeClass)

    return TypeSpec.classBuilder("FunctionStatement$parameters")
        .addModifiers(KModifier.INTERNAL)
        .addAnnotation(PublishedApi::class)
        .addTypeVariables(allTypeVariables)
        .superclass(readOnlyPropertyClass.parameterizedBy(invocableType))
        .addSuperinterface(functionWithParametersClass)

        // Constructeur primaire
        .primaryConstructor(
            FunSpec.constructorBuilder()
                .addParameter("scope", shaderBuilderScopeClass)
                .addParameter("defaultValue", invocableType)
                .addParameter("expectedInputs", listShaderType)
                .addParameter(
                    addNameParameter(
                        "annotations",
                        List::class.asClassName().parameterizedBy(String::class.asClassName())
                    ) { defaultValue("emptyList()") }
                )
                .addParameter(
                    addNameParameter(
                        "returnAnnotations",
                        List::class.asClassName().parameterizedBy(String::class.asClassName())
                    ) { defaultValue("emptyList()") }
                )
                .build()
        )
        // Appel au constructeur parent (scope, defaultValue, isFunction = true)
        .addSuperclassConstructorParameter("scope")
        .addSuperclassConstructorParameter("defaultValue")
        .addSuperclassConstructorParameter("isFunction = true")

        // Propriétés du constructeur
        .addProperty(
            PropertySpec.builder("expectedInputs", listShaderType)
                .addModifiers(KModifier.PRIVATE)
                .initializer("expectedInputs")
                .build()
        )
        .addProperty(
            PropertySpec.builder("annotations", List::class.asClassName().parameterizedBy(String::class.asClassName()))
                .addModifiers(KModifier.PRIVATE)
                .initializer("annotations")
                .build()
        )
        .addProperty(
            PropertySpec.builder("returnAnnotations", List::class.asClassName().parameterizedBy(String::class.asClassName()))
                .addModifiers(KModifier.PRIVATE)
                .initializer("returnAnnotations")
                .build()
        )

        // private val inputs = mutableListOf<FunctionInput<*>>()
        .addProperty(
            PropertySpec.builder("inputs", ClassName("kotlin.collections", "MutableList").parameterizedBy(functionInputClass.parameterizedBy(WildcardTypeName.producerOf(shaderTypeClass))))
                .addModifiers(KModifier.PRIVATE)
                .initializer("mutableListOf()")
                .build()
        )

        // override fun toString()
        .addFunction(
            FunSpec.builder("toString")
                .addModifiers(KModifier.OVERRIDE)
                .returns(String::class)
                .addCode(
                    CodeBlock.builder()
                        .beginControlFlow("return buildString")
                        .beginControlFlow("annotations.forEach { annotation ->")
                        .addStatement("append(\"@\$annotation\\n\")")
                        .endControlFlow()
                        .addStatement("val parameters = inputs.joinToString(\", \") { it.toString() }")
                        .addStatement("append(\"fn \$propertyName(\$parameters) -> \${returnAnnotations.joinToString { \"@\$it \" }}\${defaultValue.name} {\\n\")")
                        .endControlFlow()
                        .build()
                )
                .build()
        )

        // override fun addInput(input: FunctionInput<*>)
        .addFunction(
            FunSpec.builder("addInput")
                .addModifiers(KModifier.OVERRIDE)
                .addParameter("input", functionInputClass.parameterizedBy(WildcardTypeName.producerOf(shaderTypeClass)))
                .addCode(
                    CodeBlock.builder()
                        .beginControlFlow("when")
                        // inputs.size == N -> error(...)
                        .addStatement("inputs.size == %L ->", parameters)
                        .indent()
                        .addStatement("error(%S)", "Too many parameters for function")
                        .unindent()
                        // expectedInputs[inputs.size] != input.defaultValue -> error(...)
                        .addStatement("expectedInputs[inputs.size] != input.defaultValue ->")
                        .indent()
                        .addStatement("error(%P)", "Parameter type mismatch \${input.defaultValue::class.simpleName}")
                        .unindent()
                        // else -> inputs.add(input)
                        .addStatement("else -> inputs.add(input)")
                        .endControlFlow()
                        .build()
                )
                .build()
        )
        // Add force name function because vertex and fragment are not created the same way that other functions and need to change manually the name
        .addFunction(
            FunSpec.builder("forceName")
                .addParameter("name", String::class)
                .addCode("propertyName = name")
                .build()
        )
        .build()
}

// Helper local pour contourner un problème de résolution si addNameParameter n'est pas dispo directement ou si la version de poet diffère
private fun addNameParameter(name: String, type: com.squareup.kotlinpoet.TypeName, block: com.squareup.kotlinpoet.ParameterSpec.Builder.() -> Unit = {}): com.squareup.kotlinpoet.ParameterSpec {
    return com.squareup.kotlinpoet.ParameterSpec.builder(name, type).apply(block).build()
}