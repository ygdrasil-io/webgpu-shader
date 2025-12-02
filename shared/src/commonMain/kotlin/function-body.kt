package experiment.redo

inline fun ShaderFunctionBuilderScope.body(
    block: ShaderBodyBuilderScope.() -> Unit
) {
    ShaderBodyBuilderScopeImpl(this)
        .block()
}

fun ShaderBodyBuilderScope.vec4f(input1: vec3f, input2: f32): ShaderType {
    return getDefaultValue<vec4f>()
}

fun ShaderBodyBuilderScope.vec4f(input1: f32, input2: f32): ShaderType {
    return getDefaultValue<vec4f>()
}

context(scope: ShaderFunctionBuilderScope)
fun returns(type: ShaderType) {
    val subStatement = scope.pop()
    ReturnStatement(scope, type, subStatement)
        .also { scope.push(it) }
}

internal class ReturnStatement(
    scope: ShaderFunctionBuilderScope,
    val type: ShaderType,
    val subStatement: BaseStatement
) : BaseStatement(scope) {

    override fun toString(): String {
        return "\treturn $subStatement;\n"
    }
}

@PublishedApi
internal class ShaderBodyBuilderScopeImpl(
    val parent : ShaderBuilderScope,
): ShaderBodyBuilderScope {
    override fun push(statement: BaseStatement) {
        parent.push(statement)
    }

    override fun pop(): BaseStatement {
        return parent.pop()
    }
}