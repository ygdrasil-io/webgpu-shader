package experiment.redo

abstract class BaseStatement(open val scope: ShaderBuilderScope)

internal class VariableAccessStatement(
    scope: ShaderBuilderScope,
    val name: String
) : BaseStatement(scope) {
    override fun toString(): String {
        return name
    }
}
internal class VariableAssignStatement(
    scope: ShaderBuilderScope,
    val name: String,
    val value: BaseStatement
) : BaseStatement(scope) {
    override fun toString(): String {
        return "$name = $value"
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