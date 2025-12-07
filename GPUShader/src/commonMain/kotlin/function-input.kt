package io.ygdrasil.shader

@PublishedApi
internal class FunctionInput<T: ShaderType>(
    scope: ShaderBuilderScope,
    defaultValue: T,
    private val annotations: List<String> = emptyList()
): ReadOnlyPropertyBaseStatement<T>(scope, defaultValue) {

    override fun toString(): String {
        return "${annotations.joinToString { "@$it " }}$propertyName: ${defaultValue.name}"
    }
}

context(scope: ShaderFunctionBuilderScope)
inline fun <reified T : ShaderType> input() : ReadOnlyPropertyBaseStatement<T> {
    val defaultValue = getDefaultValue<T>()

    return FunctionInput(
        scope,
        defaultValue
    ).addToScope()
}

context(scope: ShaderFunctionBuilderScope)
inline fun <reified T : ShaderType> position() : ReadOnlyPropertyBaseStatement<T> {
    val defaultValue = getDefaultValue<T>()

    return FunctionInput(
        scope,
        defaultValue,
        listOf("builtin(position)")
    ).addToScope()
}