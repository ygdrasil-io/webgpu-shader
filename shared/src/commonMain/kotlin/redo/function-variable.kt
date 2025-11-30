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

inline fun <reified T : ShaderType> ShaderFunctionBuilderScope.local() : PropertyBaseStatement<T> {
    val defaultValue = getDefaultValue<T>()

    return VariableInput(
        this,
        defaultValue
    )
}