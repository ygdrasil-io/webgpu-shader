package experiment.redo

inline fun ShaderBuilderScope.vertex(
    block: ShaderFunctionBuilderScope.() -> Unit
): ReadOnlyPropertyBaseStatement<f32> {
    val defaultValue = getDefaultValue<f32>()

    val statement = FunctionStatement(
        this,
        defaultValue
    )

    ShaderFunctionBuilderScopeImpl(this)
        .block()

    return statement
}

