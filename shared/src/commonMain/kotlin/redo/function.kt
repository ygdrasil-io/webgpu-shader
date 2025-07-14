package experiment.redo

@PublishedApi
internal class FunctionStatement<T: ShaderType>(
    scope: ShaderBuilderScope,
    defaultValue: T,
    val annotations: List<String> = emptyList(),
): ReadOnlyPropertyBaseStatement<T>(scope, defaultValue) {

    override fun toString(): String {
        return "fn $propertyName() -> ${defaultValue.name}"
    }
}

inline fun <reified T : ShaderType> ShaderBuilderScope.fn(
    block: () -> Unit
): ReadOnlyPropertyBaseStatement<T> {
    val defaultValue = getDefaultValue<T>()

    block()

    return FunctionStatement(
        this,
        defaultValue
    )
}