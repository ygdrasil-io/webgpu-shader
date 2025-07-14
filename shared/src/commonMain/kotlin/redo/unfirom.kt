package experiment.redo

@PublishedApi
internal class UniformStatement<T: ShaderType>(
    val binding: Int,
    val group: Int,
    scope: ShaderBuilderScope,
    defaultValue: T
): ReadOnlyPropertyBaseStatement<T>(scope, defaultValue) {

    override fun toString(): String {
        return "@group($group) @binding($binding) var<uniform> $propertyName: ${defaultValue.name};\n"
    }
}

inline fun <reified T : ShaderType> ShaderBuilderScope.uniform(
    binding: Int, group: Int
): ReadOnlyPropertyBaseStatement<T> {
    val defaultValue = getDefaultValue<T>()

    return UniformStatement(
        binding,
        group,
        this,
        defaultValue
    )
}