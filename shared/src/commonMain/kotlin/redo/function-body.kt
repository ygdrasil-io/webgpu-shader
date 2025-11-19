package experiment.redo

inline fun ShaderFunctionBuilderScope.body(
    block: ShaderBodyBuilderScope.() -> Unit
) {
    ShaderBodyBuilderScopeImpl(this)
        .block()

    push(EndBlockStatement(this))
}

fun ShaderBodyBuilderScope.vec4f(input1: vec3f, input2: f32): ShaderType {
    return getDefaultValue<vec4f>()
}

fun ShaderBodyBuilderScope.vec4f(input1: f32, input2: f32): ShaderType {
    return getDefaultValue<vec4f>()
}

fun ShaderBodyBuilderScope.returns(type: ShaderType) {
    ReturnStatement(this, type)
}

internal class ReturnStatement(
    scope: ShaderBodyBuilderScope,
    val type: ShaderType
) : BaseStatement(scope) {
    override fun toString(): String {
        return "return "
    }
}

@PublishedApi
internal class ShaderBodyBuilderScopeImpl(
    val parent : ShaderBuilderScope,
): ShaderBodyBuilderScope {
    override fun push(statement: BaseStatement) {
        parent.push(statement)
    }
}