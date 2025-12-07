package io.ygdrasil.shader

enum class Operator(val symbol: String) {
    PLUS("+"), MINUS("-"), TIMES("*"), DIVIDE("/")
}

context(scope: ShaderBuilderScope)
internal fun operatorStatement(operator: Operator) {
    val right = scope.pop()
    val left = scope.pop()
    CompoundStatement(scope, listOf(left, OperatorStatement(scope, operator), right))
        .addToScope()
}

private class OperatorStatement(
    scope: ShaderBuilderScope,
    val operator: Operator
) : BaseStatement(scope) {
    override fun toString(): String {
        return operator.symbol
    }
}