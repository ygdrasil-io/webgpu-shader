/* This is a generated file. Do not edit! */

package experiment.redo

import kotlin.PublishedApi
import kotlin.String
import kotlin.collections.List

public interface Invocable0<T : ShaderType> : ShaderType {
  public val defaultValue: T

  context(context: ShaderBuilderScope)
  public operator fun invoke(): T
}

public interface Invocable1<T : ShaderType, I1 : ShaderType> : ShaderType {
  public val defaultValue: T

  context(context: ShaderBuilderScope)
  public operator fun invoke(i1: I1): T
}

public interface Invocable2<T : ShaderType, I1 : ShaderType, I2 : ShaderType> : ShaderType {
  public val defaultValue: T

  context(context: ShaderBuilderScope)
  public operator fun invoke(i1: I1, i2: I2): T
}

public interface Invocable3<T : ShaderType, I1 : ShaderType, I2 : ShaderType, I3 : ShaderType> : ShaderType {
  public val defaultValue: T

  context(context: ShaderBuilderScope)
  public operator fun invoke(
    i1: I1,
    i2: I2,
    i3: I3,
  ): T
}

public interface Invocable4<T : ShaderType, I1 : ShaderType, I2 : ShaderType, I3 : ShaderType, I4 : ShaderType> : ShaderType {
  public val defaultValue: T

  context(context: ShaderBuilderScope)
  public operator fun invoke(
    i1: I1,
    i2: I2,
    i3: I3,
    i4: I4,
  ): T
}

public interface Invocable5<T : ShaderType, I1 : ShaderType, I2 : ShaderType, I3 : ShaderType, I4 : ShaderType, I5 : ShaderType> : ShaderType {
  public val defaultValue: T

  context(context: ShaderBuilderScope)
  public operator fun invoke(
    i1: I1,
    i2: I2,
    i3: I3,
    i4: I4,
    i5: I5,
  ): T
}

public interface Invocable6<T : ShaderType, I1 : ShaderType, I2 : ShaderType, I3 : ShaderType, I4 : ShaderType, I5 : ShaderType, I6 : ShaderType> : ShaderType {
  public val defaultValue: T

  context(context: ShaderBuilderScope)
  public operator fun invoke(
    i1: I1,
    i2: I2,
    i3: I3,
    i4: I4,
    i5: I5,
    i6: I6,
  ): T
}

public interface Invocable7<T : ShaderType, I1 : ShaderType, I2 : ShaderType, I3 : ShaderType, I4 : ShaderType, I5 : ShaderType, I6 : ShaderType, I7 : ShaderType> : ShaderType {
  public val defaultValue: T

  context(context: ShaderBuilderScope)
  public operator fun invoke(
    i1: I1,
    i2: I2,
    i3: I3,
    i4: I4,
    i5: I5,
    i6: I6,
    i7: I7,
  ): T
}

public interface Invocable8<T : ShaderType, I1 : ShaderType, I2 : ShaderType, I3 : ShaderType, I4 : ShaderType, I5 : ShaderType, I6 : ShaderType, I7 : ShaderType, I8 : ShaderType> : ShaderType {
  public val defaultValue: T

  context(context: ShaderBuilderScope)
  public operator fun invoke(
    i1: I1,
    i2: I2,
    i3: I3,
    i4: I4,
    i5: I5,
    i6: I6,
    i7: I7,
    i8: I8,
  ): T
}

public interface Invocable9<T : ShaderType, I1 : ShaderType, I2 : ShaderType, I3 : ShaderType, I4 : ShaderType, I5 : ShaderType, I6 : ShaderType, I7 : ShaderType, I8 : ShaderType, I9 : ShaderType> : ShaderType {
  public val defaultValue: T

  context(context: ShaderBuilderScope)
  public operator fun invoke(
    i1: I1,
    i2: I2,
    i3: I3,
    i4: I4,
    i5: I5,
    i6: I6,
    i7: I7,
    i8: I8,
    i9: I9,
  ): T
}

public interface Invocable10<T : ShaderType, I1 : ShaderType, I2 : ShaderType, I3 : ShaderType, I4 : ShaderType, I5 : ShaderType, I6 : ShaderType, I7 : ShaderType, I8 : ShaderType, I9 : ShaderType, I10 : ShaderType> : ShaderType {
  public val defaultValue: T

  context(context: ShaderBuilderScope)
  public operator fun invoke(
    i1: I1,
    i2: I2,
    i3: I3,
    i4: I4,
    i5: I5,
    i6: I6,
    i7: I7,
    i8: I8,
    i9: I9,
    i10: I10,
  ): T
}

@PublishedApi
internal class Invocable0Impl<T : ShaderType>(
  override val defaultValue: T,
  private val parameters: List<ShaderType>,
) : Invocable0<T> {
  override val name: String = defaultValue.name

  public var functionProperty: ReadOnlyPropertyBaseStatement<Invocable0<T>>? = null

  context(context: ShaderBuilderScope)
  override operator fun invoke(): T {
    val functionName = functionProperty?.propertyName ?: error("fail to get function name")
    invoqueStatement(functionName, parameters)
    return defaultValue
  }
}

@PublishedApi
internal class Invocable1Impl<T : ShaderType, I1 : ShaderType>(
  override val defaultValue: T,
  private val parameters: List<ShaderType>,
) : Invocable1<T, I1> {
  override val name: String = defaultValue.name

  public var functionProperty: ReadOnlyPropertyBaseStatement<Invocable1<T, I1>>? = null

  context(context: ShaderBuilderScope)
  override operator fun invoke(i1: I1): T {
    val functionName = functionProperty?.propertyName ?: error("fail to get function name")
    invoqueStatement(functionName, parameters)
    return defaultValue
  }
}

@PublishedApi
internal class Invocable2Impl<T : ShaderType, I1 : ShaderType, I2 : ShaderType>(
  override val defaultValue: T,
  private val parameters: List<ShaderType>,
) : Invocable2<T, I1, I2> {
  override val name: String = defaultValue.name

  public var functionProperty: ReadOnlyPropertyBaseStatement<Invocable2<T, I1, I2>>? = null

  context(context: ShaderBuilderScope)
  override operator fun invoke(i1: I1, i2: I2): T {
    val functionName = functionProperty?.propertyName ?: error("fail to get function name")
    invoqueStatement(functionName, parameters)
    return defaultValue
  }
}

@PublishedApi
internal class Invocable3Impl<T : ShaderType, I1 : ShaderType, I2 : ShaderType, I3 : ShaderType>(
  override val defaultValue: T,
  private val parameters: List<ShaderType>,
) : Invocable3<T, I1, I2, I3> {
  override val name: String = defaultValue.name

  public var functionProperty: ReadOnlyPropertyBaseStatement<Invocable3<T, I1, I2, I3>>? = null

  context(context: ShaderBuilderScope)
  override operator fun invoke(
    i1: I1,
    i2: I2,
    i3: I3,
  ): T {
    val functionName = functionProperty?.propertyName ?: error("fail to get function name")
    invoqueStatement(functionName, parameters)
    return defaultValue
  }
}

@PublishedApi
internal class Invocable4Impl<T : ShaderType, I1 : ShaderType, I2 : ShaderType, I3 : ShaderType, I4 : ShaderType>(
  override val defaultValue: T,
  private val parameters: List<ShaderType>,
) : Invocable4<T, I1, I2, I3, I4> {
  override val name: String = defaultValue.name

  public var functionProperty: ReadOnlyPropertyBaseStatement<Invocable4<T, I1, I2, I3, I4>>? = null

  context(context: ShaderBuilderScope)
  override operator fun invoke(
    i1: I1,
    i2: I2,
    i3: I3,
    i4: I4,
  ): T {
    val functionName = functionProperty?.propertyName ?: error("fail to get function name")
    invoqueStatement(functionName, parameters)
    return defaultValue
  }
}

@PublishedApi
internal class Invocable5Impl<T : ShaderType, I1 : ShaderType, I2 : ShaderType, I3 : ShaderType, I4 : ShaderType, I5 : ShaderType>(
  override val defaultValue: T,
  private val parameters: List<ShaderType>,
) : Invocable5<T, I1, I2, I3, I4, I5> {
  override val name: String = defaultValue.name

  public var functionProperty: ReadOnlyPropertyBaseStatement<Invocable5<T, I1, I2, I3, I4, I5>>? =
      null

  context(context: ShaderBuilderScope)
  override operator fun invoke(
    i1: I1,
    i2: I2,
    i3: I3,
    i4: I4,
    i5: I5,
  ): T {
    val functionName = functionProperty?.propertyName ?: error("fail to get function name")
    invoqueStatement(functionName, parameters)
    return defaultValue
  }
}

@PublishedApi
internal class Invocable6Impl<T : ShaderType, I1 : ShaderType, I2 : ShaderType, I3 : ShaderType, I4 : ShaderType, I5 : ShaderType, I6 : ShaderType>(
  override val defaultValue: T,
  private val parameters: List<ShaderType>,
) : Invocable6<T, I1, I2, I3, I4, I5, I6> {
  override val name: String = defaultValue.name

  public var functionProperty: ReadOnlyPropertyBaseStatement<Invocable6<T, I1, I2, I3, I4, I5, I6>>?
      = null

  context(context: ShaderBuilderScope)
  override operator fun invoke(
    i1: I1,
    i2: I2,
    i3: I3,
    i4: I4,
    i5: I5,
    i6: I6,
  ): T {
    val functionName = functionProperty?.propertyName ?: error("fail to get function name")
    invoqueStatement(functionName, parameters)
    return defaultValue
  }
}

@PublishedApi
internal class Invocable7Impl<T : ShaderType, I1 : ShaderType, I2 : ShaderType, I3 : ShaderType, I4 : ShaderType, I5 : ShaderType, I6 : ShaderType, I7 : ShaderType>(
  override val defaultValue: T,
  private val parameters: List<ShaderType>,
) : Invocable7<T, I1, I2, I3, I4, I5, I6, I7> {
  override val name: String = defaultValue.name

  public var functionProperty:
      ReadOnlyPropertyBaseStatement<Invocable7<T, I1, I2, I3, I4, I5, I6, I7>>? = null

  context(context: ShaderBuilderScope)
  override operator fun invoke(
    i1: I1,
    i2: I2,
    i3: I3,
    i4: I4,
    i5: I5,
    i6: I6,
    i7: I7,
  ): T {
    val functionName = functionProperty?.propertyName ?: error("fail to get function name")
    invoqueStatement(functionName, parameters)
    return defaultValue
  }
}

@PublishedApi
internal class Invocable8Impl<T : ShaderType, I1 : ShaderType, I2 : ShaderType, I3 : ShaderType, I4 : ShaderType, I5 : ShaderType, I6 : ShaderType, I7 : ShaderType, I8 : ShaderType>(
  override val defaultValue: T,
  private val parameters: List<ShaderType>,
) : Invocable8<T, I1, I2, I3, I4, I5, I6, I7, I8> {
  override val name: String = defaultValue.name

  public var functionProperty:
      ReadOnlyPropertyBaseStatement<Invocable8<T, I1, I2, I3, I4, I5, I6, I7, I8>>? = null

  context(context: ShaderBuilderScope)
  override operator fun invoke(
    i1: I1,
    i2: I2,
    i3: I3,
    i4: I4,
    i5: I5,
    i6: I6,
    i7: I7,
    i8: I8,
  ): T {
    val functionName = functionProperty?.propertyName ?: error("fail to get function name")
    invoqueStatement(functionName, parameters)
    return defaultValue
  }
}

@PublishedApi
internal class Invocable9Impl<T : ShaderType, I1 : ShaderType, I2 : ShaderType, I3 : ShaderType, I4 : ShaderType, I5 : ShaderType, I6 : ShaderType, I7 : ShaderType, I8 : ShaderType, I9 : ShaderType>(
  override val defaultValue: T,
  private val parameters: List<ShaderType>,
) : Invocable9<T, I1, I2, I3, I4, I5, I6, I7, I8, I9> {
  override val name: String = defaultValue.name

  public var functionProperty:
      ReadOnlyPropertyBaseStatement<Invocable9<T, I1, I2, I3, I4, I5, I6, I7, I8, I9>>? = null

  context(context: ShaderBuilderScope)
  override operator fun invoke(
    i1: I1,
    i2: I2,
    i3: I3,
    i4: I4,
    i5: I5,
    i6: I6,
    i7: I7,
    i8: I8,
    i9: I9,
  ): T {
    val functionName = functionProperty?.propertyName ?: error("fail to get function name")
    invoqueStatement(functionName, parameters)
    return defaultValue
  }
}

@PublishedApi
internal class Invocable10Impl<T : ShaderType, I1 : ShaderType, I2 : ShaderType, I3 : ShaderType, I4 : ShaderType, I5 : ShaderType, I6 : ShaderType, I7 : ShaderType, I8 : ShaderType, I9 : ShaderType, I10 : ShaderType>(
  override val defaultValue: T,
  private val parameters: List<ShaderType>,
) : Invocable10<T, I1, I2, I3, I4, I5, I6, I7, I8, I9, I10> {
  override val name: String = defaultValue.name

  public var functionProperty:
      ReadOnlyPropertyBaseStatement<Invocable10<T, I1, I2, I3, I4, I5, I6, I7, I8, I9, I10>>? = null

  context(context: ShaderBuilderScope)
  override operator fun invoke(
    i1: I1,
    i2: I2,
    i3: I3,
    i4: I4,
    i5: I5,
    i6: I6,
    i7: I7,
    i8: I8,
    i9: I9,
    i10: I10,
  ): T {
    val functionName = functionProperty?.propertyName ?: error("fail to get function name")
    invoqueStatement(functionName, parameters)
    return defaultValue
  }
}
