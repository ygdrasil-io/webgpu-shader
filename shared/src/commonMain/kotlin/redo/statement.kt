package experiment.redo


abstract class BaseStatement(scope: ShaderBuilderScope) {

    init {
        scope.push(this)
    }
}