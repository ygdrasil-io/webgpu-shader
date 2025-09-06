package experiment.redo

sealed interface ShaderBuilderScope {
    fun push(statement: BaseStatement)
}
sealed interface ShaderFunctionBuilderScope : ShaderBuilderScope
sealed interface ShaderBodyBuilderScope : ShaderBuilderScope
