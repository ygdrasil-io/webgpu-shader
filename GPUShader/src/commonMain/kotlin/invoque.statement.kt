package io.ygdrasil.shader

context(scope: ShaderBuilderScope)
internal fun invoqueStatement(
    functionName: String,
    parameters: List<ShaderType>
) {
    InvoqueStatement(scope,
        functionName,
        // TODO: add type checking if needed, but I think that cover by DSL
        parameters.map { scope.pop() }.reversed()
    ).addToScope()
}

internal class InvoqueStatement(
    scope: ShaderBuilderScope,
    val functionName: String,
    val parameters: List<BaseStatement>
) : BaseStatement(scope) {
    override fun toString(): String {
        return "$functionName(${parameters.joinToString(", ")})"
    }
}