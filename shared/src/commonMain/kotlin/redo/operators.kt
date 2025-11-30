package experiment.redo

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