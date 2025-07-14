package experiment.redo


fun shader(block: ShaderBuilderScope.() -> Unit): ShaderSource {
    return ShaderBuilderScopeImpl()
        .also(block)
        .build()
}

data class ShaderSource(val source: String)

internal class ShaderBuilderScopeImpl : ShaderBuilderScope {

    val statements = mutableListOf<BaseStatement>()

    override fun push(statement: BaseStatement) {
        statements.add(statement)
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