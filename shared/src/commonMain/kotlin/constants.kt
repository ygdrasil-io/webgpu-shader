package experiment.redo

context(scope: ShaderBuilderScope)
fun f32(value: Double): f32 {
    ConstantStatement(scope, value.toString())
        .also { scope.push(it) }
    return getDefaultValue()
}

context(scope: ShaderBuilderScope)
fun i32(value: Int): f32 {
    ConstantStatement(scope, value.toString())
        .also { scope.push(it) }
    return getDefaultValue()
}

context(scope: ShaderBuilderScope)
fun bool(value: Boolean): f32 {
    ConstantStatement(scope, value.toString())
        .also { scope.push(it) }
    return getDefaultValue()
}


internal class ConstantStatement(
    scope: ShaderBuilderScope,
    val value: String
) : BaseStatement(scope) {

    override fun toString(): String {
        return value
    }
}
