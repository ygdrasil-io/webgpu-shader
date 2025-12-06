package experiment.redo

sealed interface ShaderBuilderScope {
    fun push(statement: BaseStatement)
    fun pop(): BaseStatement
}

sealed interface ShaderFunctionBuilderScope : ShaderBuilderScope

@PublishedApi
internal inline fun <reified T : BaseStatement> T.addToScope(): T = apply {
    scope.push(this)
}