package experiment.redo

import kotlin.jvm.JvmName

context(scope: ShaderBuilderScope)
inline fun <reified T : ShaderType> fn(
    block: ShaderFunctionBuilderScope.() -> Unit
): ReadOnlyPropertyBaseStatement<Invocable0<T>> {
    val defaultValue = getDefaultValue<T>()

    val statement = FunctionStatement0(
        scope,
        Invocable0Impl(defaultValue)
    ).also { scope.push(it) }

    ShaderFunctionBuilderScopeImpl(scope, statement)
        .block()
    // Add end block statement to stack
    EndBlockStatement(scope)
        .also { scope.push(it) }
    return statement
}

context(scope: ShaderBuilderScope)
@JvmName("fn1")
inline fun <reified T : ShaderType, reified I1 : ShaderType> fn(
    block: ShaderFunctionBuilderScope.() -> Unit
): ReadOnlyPropertyBaseStatement<Invocable1<T, I1>> {
    val defaultValue = getDefaultValue<T>()
    val i1 = getDefaultValue<I1>()

    val statement = FunctionStatement1(
        scope,
        Invocable1Impl<T, I1>(defaultValue),
        listOf(i1)
    ).also { scope.push(it) }

    ShaderFunctionBuilderScopeImpl(scope, statement)
        .block()

    // Add end block statement to stack
    EndBlockStatement(scope)
        .also { scope.push(it) }
    return statement
}

context(scope: ShaderBuilderScope)
@JvmName("fn2")
inline fun <reified T : ShaderType, reified I1 : ShaderType, reified I2 : ShaderType> fn(
    block: ShaderFunctionBuilderScope.() -> Unit
): ReadOnlyPropertyBaseStatement<Invocable2<T, I1, I2>> {
    val defaultValue = getDefaultValue<T>()
    val i1 = getDefaultValue<I1>()
    val i2 = getDefaultValue<I2>()

    val statement = FunctionStatement2(
        scope,
        Invocable2Impl<T, I1, I2>(defaultValue),
        listOf(i1, i2)
    ).also { scope.push(it) }

    ShaderFunctionBuilderScopeImpl(scope, statement)
        .block()

    // Add end block statement to stack
    EndBlockStatement(scope)
        .also { scope.push(it) }
    return statement
}

@PublishedApi
internal class FunctionStatement0<T: ShaderType>(
    scope: ShaderBuilderScope,
    defaultValue: Invocable0<T>,
    val annotations: List<String> = emptyList(),
): ReadOnlyPropertyBaseStatement<Invocable0<T>>(scope, defaultValue, isFunction = true),
    FunctionWithParameters {

    override fun toString(): String = buildString {
        annotations.forEach { annotation ->
            append("@$annotation\n")
        }
        append("fn $propertyName() -> ${defaultValue.name} {\n")
    }

    override fun addInput(input: FunctionInput<*>) {
        error("Too many parameters for function")
    }
}

@PublishedApi
internal class FunctionStatement1<T: ShaderType, I1: ShaderType>(
    scope: ShaderBuilderScope,
    defaultValue: Invocable1<T, I1>,
    val expectedInputs: List<ShaderType>,
    val annotations: List<String> = emptyList(),
): ReadOnlyPropertyBaseStatement<Invocable1<T, I1>>(scope, defaultValue, isFunction = true),
    FunctionWithParameters {

    private val inputs = mutableListOf<FunctionInput<*>>()

    override fun toString(): String = buildString {
        annotations.forEach { annotation ->
            append("@$annotation\n")
        }
        val parameters = inputs.joinToString(", ") { it.toString() }.orEmpty()
        append("fn $propertyName($parameters) -> ${defaultValue.name} {\n")
    }

    override fun addInput(input: FunctionInput<*>) {
        when {
            inputs.isNotEmpty() -> {
                error("Too many parameters for function")
            }
            expectedInputs[inputs.size] != input.defaultValue -> {
                error("Parameter type mismatch ${input.defaultValue::class.simpleName}")
            }
            else -> inputs.add(input)
        }
    }

}


@PublishedApi
internal class FunctionStatement2<T: ShaderType, I1: ShaderType, I2: ShaderType>(
    scope: ShaderBuilderScope,
    defaultValue: Invocable2<T, I1, I2>,
    val expectedInputs: List<ShaderType>,
    val annotations: List<String> = emptyList(),
): ReadOnlyPropertyBaseStatement<Invocable2<T, I1, I2>>(scope, defaultValue, isFunction = true),
    FunctionWithParameters {

    private val inputs = mutableListOf<FunctionInput<*>>()

    override fun toString(): String = buildString {
        annotations.forEach { annotation ->
            append("@$annotation\n")
        }
        val parameters = inputs.joinToString(", ") { it.toString() }.orEmpty()
        append("fn $propertyName($parameters) -> ${defaultValue.name} {\n")
    }

    override fun addInput(input: FunctionInput<*>) {
        when {
            inputs.size == 2 -> {
                error("Too many parameters for function")
            }
            expectedInputs[inputs.size] != input.defaultValue -> {
                error("Parameter type mismatch ${input.defaultValue::class.simpleName}")
            }
            else -> inputs.add(input)
        }
    }

}

@PublishedApi
internal class ShaderFunctionBuilderScopeImpl(
    private val parent: ShaderBuilderScope,
    private val functionStatement: FunctionWithParameters
): ShaderFunctionBuilderScope {

    override fun push(statement: BaseStatement) {
        when (statement) {
            is FunctionInput<*> -> {
                functionStatement.addInput(statement)
            }

            else -> parent.push(statement)
        }
    }

    override fun pop(): BaseStatement {
        return parent.pop()
    }
}

internal interface FunctionWithParameters {
    fun addInput(input: FunctionInput<*>)
}