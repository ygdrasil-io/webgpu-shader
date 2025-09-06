package experiment.redo

abstract class BaseStatement(val scope: ShaderBuilderScope) {

    init {
        scope.push(this)
    }
}

enum class Operator(val symbol: String) {
    PLUS("+"), MINUS("-"), TIMES("*"), DIVIDE("/")
}

internal class OperatorStatement(
    scope: ShaderBuilderScope,
    val operator: Operator
) : BaseStatement(scope) {
    override fun toString(): String {
        return operator.symbol
    }
}

internal class VariableStatement(
    scope: ShaderBuilderScope,
    val name: String
) : BaseStatement(scope) {
    override fun toString(): String {
        return name
    }
}

internal class PropertyStatement(
    scope: ShaderBuilderScope,
    val name: String
) : BaseStatement(scope) {
    override fun toString(): String {
        return ".$name"
    }
}