package experiment.redo


@PublishedApi
internal class Invocable0Impl<T: ShaderType>(override val defaultValue: T) : Invocable0<T> {
    override val name: String = defaultValue.name
}

@PublishedApi
internal class Invocable1Impl<T: ShaderType, AI: ShaderType>(override val defaultValue: T) : Invocable1<T, AI> {
    override val name: String = defaultValue.name
}

@PublishedApi
internal class Invocable2Impl<T: ShaderType, AI: ShaderType, A2: ShaderType>(override val defaultValue: T) : Invocable2<T, AI, A2> {
    override val name: String = defaultValue.name
}