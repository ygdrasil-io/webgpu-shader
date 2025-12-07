package io.ygdrasil.shader

import kotlin.reflect.KProperty

@PublishedApi
internal class LocalVariableStatement<T: ShaderType>(
    scope: ShaderBuilderScope,
    defaultValue: T,
    val initialValue: BaseStatement? = null
): PropertyBaseStatement<T>(scope, defaultValue) {

    private var mutable = false

    override operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        mutable = true
        super.setValue(thisRef, property, value)
    }

    override fun toString(): String {
        return "${mutabilityKeyword()} $propertyName: ${defaultValue.name}${initializationString()}"
    }

    private fun initializationString(): String = initialValue?.let { " = $it" } ?: ""

    private fun mutabilityKeyword(): String = "var".takeIf { mutable } ?: "let"
}

context(scope: ShaderFunctionBuilderScope)
inline fun <reified T : ShaderType> local(value: T? = null) : PropertyBaseStatement<T> {
    val defaultValue = getDefaultValue<T>()

    val initialValue = value?.let { _ -> scope.pop()}

    return LocalVariableStatement(
        scope,
        defaultValue,
        initialValue
    ).addToScope()
}