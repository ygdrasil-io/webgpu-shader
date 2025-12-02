package experiment.redo


fun shader(block: ShaderBuilderScope.() -> Unit): ShaderSource {
    return ShaderBuilderScopeImpl()
        .also(block)
        .build()
}

data class ShaderSource(val source: String)

 class ShaderBuilderScopeImpl : ShaderBuilderScope {

    val statements = mutableListOf<BaseStatement>()
    val partialStatements = mutableListOf<BaseStatement>()

    override fun push(statement: BaseStatement) {

        when (statement) {
            is PropertyStatement,
            is VariableStatement,
            is OperatorStatement,
            is ConstantStatement -> {
                partialStatements.add(statement)
            }

            else -> statements.add(statement)
        }

    }

     override fun pop(): BaseStatement {
         partialStatements.removeLastOrNull()
             ?.let { return it }

         error("No partial statement to pop")
     }

     fun build(): ShaderSource {
        val source = StringBuilder()
        statements.forEach {
            source.append(it.toString())
        }
        return ShaderSource(
            source.toString()
        )
    }
}