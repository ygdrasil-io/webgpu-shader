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

context(scope: ShaderBuilderScope)
internal fun propertyStatement(name: String) {
    val property = PropertyStatement(scope, name)
    val value = scope.pop()
    CompoundStatement(scope, listOf(value, property), "")
        .addToScope()
}

private class PropertyStatement(
    scope: ShaderBuilderScope,
    val name: String
) : BaseStatement(scope) {
    override fun toString(): String {
        return ".$name"
    }
}

internal class CompoundStatement(
    scope: ShaderBuilderScope,
    val statements: List<BaseStatement>,
    val separator: String = " "
) : BaseStatement(scope) {
    override fun toString(): String {
        return statements.joinToString(separator)
    }
}