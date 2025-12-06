package experiment.redo

inline fun ShaderBuilderScope.vertex(
    name: String = "mainVertex",
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
    statement.forceName(name)

    statement.addToScope()

    ShaderFunctionBuilderScopeImpl(this, statement)
        .block()

    EndBlockStatement(this).addToScope()
}

