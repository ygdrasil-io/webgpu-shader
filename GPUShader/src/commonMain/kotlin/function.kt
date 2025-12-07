package io.ygdrasil.shader

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