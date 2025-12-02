package experiment.redo

@PublishedApi
internal class VariableInput<T: ShaderType>(
    scope: ShaderBuilderScope,
    defaultValue: T
): PropertyBaseStatement<T>(scope, defaultValue) {

    override fun toString(): String {
        return "$propertyName: ${defaultValue.name};\n"
    }
}

context(scope: ShaderFunctionBuilderScope)
inline fun <reified T : ShaderType> local() : PropertyBaseStatement<T> {
    val defaultValue = getDefaultValue<T>()

    return VariableInput(
        scope,
        defaultValue
    )
}