package experiment.redo

fun ShaderBuilderScope.f32(value: Double): f32 {
    ConstantStatement(this, value.toString())
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
