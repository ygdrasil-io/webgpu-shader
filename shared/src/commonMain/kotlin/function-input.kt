package experiment.redo

@PublishedApi
internal class FunctionInput<T: ShaderType>(
    scope: ShaderBuilderScope,
    defaultValue: T
): ReadOnlyPropertyBaseStatement<T>(scope, defaultValue) {

    override fun toString(): String {
        return "$propertyName: ${defaultValue.name}"
    }
}

context(scope: ShaderFunctionBuilderScope)
inline fun <reified T : ShaderType> input() : ReadOnlyPropertyBaseStatement<T> {
    val defaultValue = getDefaultValue<T>()

    return FunctionInput(
        scope,
        defaultValue
    ).addToScope()
}