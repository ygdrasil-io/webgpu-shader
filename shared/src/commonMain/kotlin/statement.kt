package experiment.redo

abstract class BaseStatement(open val scope: ShaderBuilderScope)

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

internal class CompoundStatement(
    scope: ShaderBuilderScope,
    val statements: List<BaseStatement>
) : BaseStatement(scope) {
    override fun toString(): String {
        return statements.joinToString(" ")
    }
}