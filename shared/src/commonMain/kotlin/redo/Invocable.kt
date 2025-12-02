package experiment.redo

sealed interface Invocable0<T: ShaderType>: ShaderType {

    val defaultValue: T

    operator fun invoke(): T {
        return defaultValue
    }

}

@PublishedApi
internal class Invocable0Impl<T: ShaderType>(override val defaultValue: T) : Invocable0<T> {
    override val name: String = defaultValue.name
}

interface Invocable1<T: ShaderType, I1: ShaderType>: ShaderType {

    val defaultValue: T

    operator fun invoke(i1: I1): T {
        return defaultValue
    }

}

interface Invocable2<T: ShaderType, I1: ShaderType, I2: ShaderType>: ShaderType {

    val defaultValue: T

    operator fun invoke(i1: I1, i2: I2): T {
        return defaultValue
    }

}

@PublishedApi
internal class Invocable1Impl<T: ShaderType, AI: ShaderType>(override val defaultValue: T) : Invocable1<T, AI> {
    override val name: String = defaultValue.name
}

@PublishedApi
internal class Invocable2Impl<T: ShaderType, AI: ShaderType, A2: ShaderType>(override val defaultValue: T) : Invocable2<T, AI, A2> {
    override val name: String = defaultValue.name
}