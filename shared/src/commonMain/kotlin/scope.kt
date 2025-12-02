package experiment.redo

sealed interface ShaderBuilderScope {
    fun push(statement: BaseStatement)
    fun pop(): BaseStatement
}
sealed interface ShaderFunctionBuilderScope : ShaderBuilderScope
sealed interface ShaderBodyBuilderScope : ShaderBuilderScope
