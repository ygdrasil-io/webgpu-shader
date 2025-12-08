package io.ygdrasil.shader

@PublishedApi
internal class PrivateStatement<T>(
    val binding: Int,
    val group: Int,
    scope: ShaderBuilderScope,
    defaultValue: T
): ReadOnlyPropertyBaseStatement<T>(scope, defaultValue)

inline fun <reified T : ShaderType> ShaderBuilderScope.private(
    binding: Int, group: Int
): ReadOnlyPropertyBaseStatement<T> {
    val defaultValue = getDefaultValue<T>()

    return UniformStatement(
        binding,
        group,
        this,
        defaultValue
    )
}