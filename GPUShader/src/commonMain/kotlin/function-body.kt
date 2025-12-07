package io.ygdrasil.shader

context(context: ShaderBuilderScope)
fun vec4f(input1: vec3f, input2: f32): vec4f {
    invoqueStatement("vec4f", listOf(input1, input2))
    return getDefaultValue<vec4f>()
}

context(context: ShaderBuilderScope)
fun vec4f(input1: f32, input2: f32, input3: f32, input4: f32): vec4f {
    invoqueStatement("vec4f", listOf(input1, input2, input3, input4))
    return getDefaultValue<vec4f>()
}

context(scope: ShaderFunctionBuilderScope)
fun returning(value: ShaderType) {
    val subStatement = scope.pop()
    ReturnStatement(scope, value, subStatement)
        .addToScope()
}

internal class ReturnStatement(
    scope: ShaderFunctionBuilderScope,
    val type: ShaderType,
    val subStatement: BaseStatement
) : BaseStatement(scope) {

    override fun toString(): String {
        return "return $subStatement"
    }
}
