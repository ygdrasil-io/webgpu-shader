package experiment.redo

abstract class BaseStatement(val scope: ShaderBuilderScope) {

    init {
        scope.push(this)
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