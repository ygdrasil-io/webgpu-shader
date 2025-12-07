package experiment.redo

@PublishedApi
internal class VariableStatement<T: ShaderType>(
    scope: ShaderBuilderScope,
    defaultValue: T
): PropertyBaseStatement<T>(scope, defaultValue) {

    override fun toString(): String {
        return "var $propertyName: ${defaultValue.name}"
    }
}

context(scope: ShaderFunctionBuilderScope)
inline fun <reified T : ShaderType> local(value: T? = null) : PropertyBaseStatement<T> {
    val defaultValue = getDefaultValue<T>()

    return VariableStatement(
        scope,
        defaultValue
    ).addToScope()
}