package experiment.redo

@PublishedApi
internal class EndBlockStatement(
    scope: ShaderBuilderScope,
) : BaseStatement(scope) {
    override fun toString(): String {
        return "}"
    }
}

internal interface StartBlockStatement