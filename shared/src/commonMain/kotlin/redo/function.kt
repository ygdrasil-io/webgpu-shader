package experiment.redo

inline fun <reified T : ShaderType> ShaderBuilderScope.fn(
    block: ShaderFunctionBuilderScope.() -> Unit
): ReadOnlyPropertyBaseStatement<T> {
    val defaultValue = getDefaultValue<T>()

    val statement = FunctionStatement(
        this,
        defaultValue
    )

    ShaderFunctionBuilderScopeImpl(this)
        .block()

    return statement
}

@PublishedApi
internal class FunctionStatement<T: ShaderType>(
    scope: ShaderBuilderScope,
    defaultValue: T,
    val annotations: List<String> = emptyList(),
): ReadOnlyPropertyBaseStatement<T>(scope, defaultValue) {

    override fun toString(): String {
        return "fn $propertyName() -> ${defaultValue.name} {"
    }
}

@PublishedApi
internal class ShaderFunctionBuilderScopeImpl(
    private val parent: ShaderBuilderScope
): ShaderFunctionBuilderScope {
    override fun push(statement: BaseStatement) {
        parent.push(statement)
    }

}