package experiment.redo

inline fun ShaderBuilderScope.vertex(
    name: String = "mainVertex",
    block: ShaderFunctionBuilderScope.() -> Unit
) {
    val defaultValue = getDefaultValue<vec4f>()
    val i1 = getDefaultValue<vec4f>()
    val invocable1 = Invocable1Impl<vec4f, vec4f>(defaultValue, listOf(i1))

    val statement = FunctionStatement1(
        this,
        invocable1,
        listOf(getDefaultValue<vec4f>()),
        listOf("vertex"),
        listOf("builtin(position)")
    )
    statement.forceName(name)
    invocable1.functionProperty = statement

    statement.addToScope()

    ShaderFunctionBuilderScopeImpl(this, statement)
        .block()

    EndBlockStatement(this).addToScope()
}

