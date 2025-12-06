package experiment.redo

context(scope: ShaderBuilderScope)
internal fun requestInvoqueStatement(
    functionName: String,
    parameters: List<ShaderType>
) {
    InvoqueStatement(scope,
        functionName,
        // TODO: add type checking
        parameters.map { scope.pop() }.reversed()
    ).addToScope()
}

internal class InvoqueStatement(
    scope: ShaderBuilderScope,
    val functionName: String,
    val parameters: List<BaseStatement>
) : BaseStatement(scope) {
    override fun toString(): String {
        return "$functionName(${parameters.joinToString(", ")})"
    }
}