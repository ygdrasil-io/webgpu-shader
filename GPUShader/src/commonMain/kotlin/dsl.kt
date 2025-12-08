package io.ygdrasil.shader

import kotlin.jvm.JvmInline

fun shader(block: ShaderBuilderScope.() -> Unit): ShaderSource {
    return ShaderBuilderScopeImpl()
        .also(block)
        .build()
}

@JvmInline
value class ShaderSource(val source: String)

internal class ShaderBuilderScopeImpl : ShaderBuilderScope {

    val statements = mutableListOf<BaseStatement>()

    override fun push(statement: BaseStatement) {
        statements.add(statement)
    }

    override fun pop(): BaseStatement {
        statements.removeLastOrNull()
            ?.let { return it }

        error("No partial statement to pop")
    }

    /**
     * Builds and returns a complete shader source code representation as a string.
     * The method processes a list of shader statements, maintaining proper indentation levels
     * and formatting the source code with semicolons and line breaks where appropriate.
     *
     * Note: This API is experimental and may change in future releases
     * @return A `ShaderSource` instance containing the formatted shader source code as a string.
     */
    internal fun build(): ShaderSource {
        var indent = 0
        val source = StringBuilder()
        statements.forEach {
            // fn, if ...
            if (it is EndBlockStatement) indent--
            source.append("    ".repeat(indent))
            // Actual statement
            source.append("$it")
            // End of statement
            when (it) {
                is StartBlockStatement -> indent++
                // Do not append semicolon for end block statements
                is EndBlockStatement -> {}
                else -> source.append(";")
            }
            // New line
            source.append("\n")
        }
        return ShaderSource(
            source.toString()
        )
    }
}