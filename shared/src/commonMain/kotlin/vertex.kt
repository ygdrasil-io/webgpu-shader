package experiment.redo

inline fun ShaderBuilderScope.vertex(
    block: ShaderFunctionBuilderScope.() -> Unit
) {
    val defaultValue = getDefaultValue<vec4f>()

    val statement = FunctionStatement1(
        this,
        Invocable1Impl(defaultValue),
        listOf(getDefaultValue<vec4f>()),
        listOf("vertex"),
        listOf("builtin(position)")
    )

    statement.addToScope()

    ShaderFunctionBuilderScopeImpl(this, statement)
        .block()

    EndBlockStatement(this).addToScope()
}

