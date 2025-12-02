package experiment.redo

inline fun ShaderBuilderScope.vertex(
    block: ShaderFunctionBuilderScope.() -> Unit
): ReadOnlyPropertyBaseStatement<Invocable0<f32>> {
    val defaultValue = getDefaultValue<f32>()

    val statement = FunctionStatement0(
        this,
        Invocable0Impl(defaultValue),
        listOf("vertex")
    )

    ShaderFunctionBuilderScopeImpl(this, statement)
        .block()

    return statement
}

