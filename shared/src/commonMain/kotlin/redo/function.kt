package experiment.redo

import kotlin.jvm.JvmName

inline fun <reified T : ShaderType> ShaderBuilderScope.fn(
    block: ShaderFunctionBuilderScope.() -> Unit
): ReadOnlyPropertyBaseStatement<Invocable0<T>> {
    val defaultValue = getDefaultValue<T>()

    val statement = FunctionStatement0(
        this,
        Invocable0Impl(defaultValue)
    )

    ShaderFunctionBuilderScopeImpl(this)
        .block()

    return statement
}

@JvmName("fn1")
inline fun <reified T : ShaderType, reified I1 : ShaderType> ShaderBuilderScope.fn(
    block: ShaderFunctionBuilderScope.() -> Unit
): ReadOnlyPropertyBaseStatement<Invocable1<T, I1>> {
    val defaultValue = getDefaultValue<T>()
    val i1 = getDefaultValue<I1>()

    val statement = FunctionStatement1(
        this,
        Invocable1Impl<T, I1>(defaultValue)
    )

    ShaderFunctionBuilderScopeImpl(this)
        .block()

    return statement
}

@PublishedApi
internal class FunctionStatement0<T: ShaderType>(
    scope: ShaderBuilderScope,
    defaultValue: Invocable0<T>,
    val annotations: List<String> = emptyList(),
): ReadOnlyPropertyBaseStatement<Invocable0<T>>(scope, defaultValue, isFunction = true) {

    override fun toString(): String = buildString {
        annotations.forEach { annotation ->
            append("@$annotation\n")
        }
        append("fn $propertyName() -> ${defaultValue.name} {")
    }
}

@PublishedApi
internal class FunctionStatement1<T: ShaderType, I1: ShaderType>(
    scope: ShaderBuilderScope,
    defaultValue: Invocable1<T, I1>,
    val annotations: List<String> = emptyList(),
): ReadOnlyPropertyBaseStatement<Invocable1<T, I1>>(scope, defaultValue, isFunction = true) {

    override fun toString(): String = buildString {
        annotations.forEach { annotation ->
            append("@$annotation\n")
        }
        append("fn $propertyName() -> ${defaultValue.name} {")
    }

}

@PublishedApi
internal class ShaderFunctionBuilderScopeImpl(
    private val parent: ShaderBuilderScope
): ShaderFunctionBuilderScope {
    override fun push(statement: BaseStatement) {
        parent.push(statement)
    }

}