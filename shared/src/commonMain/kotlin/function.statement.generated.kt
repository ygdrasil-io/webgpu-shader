/* This is a generated file. Do not edit! */

package experiment.redo

import kotlin.PublishedApi
import kotlin.String
import kotlin.collections.List
import kotlin.collections.MutableList

@PublishedApi
internal class FunctionStatement0<T : ShaderType>(
  scope: ShaderBuilderScope,
  defaultValue: Invocable0<T>,
  public val expectedInputs: List<ShaderType>,
  public val annotations: List<String> = emptyList(),
) : ReadOnlyPropertyBaseStatement<Invocable0<T>>(scope, defaultValue, isFunction = true),
    FunctionWithParameters {
  private val inputs: MutableList<FunctionInput<out ShaderType>> = mutableListOf()

  override fun toString(): String = buildString {
    annotations.forEach { annotation ->
      append("@$annotation\n")
    }
    val parameters = inputs.joinToString(", ") { it.toString() }.orEmpty()
    append("fn $propertyName($parameters) -> ${defaultValue.name} {\n")
  }

  override fun addInput(input: FunctionInput<out ShaderType>) {
    when {
      inputs.size == 0 ->
        error("Too many parameters for function")
      expectedInputs[inputs.size] != input.defaultValue ->
        error("""Parameter type mismatch ${input.defaultValue::class.simpleName}""")
      else -> inputs.add(input)
    }
  }
}

@PublishedApi
internal class FunctionStatement1<T : ShaderType, I1 : ShaderType>(
  scope: ShaderBuilderScope,
  defaultValue: Invocable1<T, I1>,
  public val expectedInputs: List<ShaderType>,
  public val annotations: List<String> = emptyList(),
) : ReadOnlyPropertyBaseStatement<Invocable1<T, I1>>(scope, defaultValue, isFunction = true),
    FunctionWithParameters {
  private val inputs: MutableList<FunctionInput<out ShaderType>> = mutableListOf()

  override fun toString(): String = buildString {
    annotations.forEach { annotation ->
      append("@$annotation\n")
    }
    val parameters = inputs.joinToString(", ") { it.toString() }.orEmpty()
    append("fn $propertyName($parameters) -> ${defaultValue.name} {\n")
  }

  override fun addInput(input: FunctionInput<out ShaderType>) {
    when {
      inputs.size == 1 ->
        error("Too many parameters for function")
      expectedInputs[inputs.size] != input.defaultValue ->
        error("""Parameter type mismatch ${input.defaultValue::class.simpleName}""")
      else -> inputs.add(input)
    }
  }
}

@PublishedApi
internal class FunctionStatement2<T : ShaderType, I1 : ShaderType, I2 : ShaderType>(
  scope: ShaderBuilderScope,
  defaultValue: Invocable2<T, I1, I2>,
  public val expectedInputs: List<ShaderType>,
  public val annotations: List<String> = emptyList(),
) : ReadOnlyPropertyBaseStatement<Invocable2<T, I1, I2>>(scope, defaultValue, isFunction = true),
    FunctionWithParameters {
  private val inputs: MutableList<FunctionInput<out ShaderType>> = mutableListOf()

  override fun toString(): String = buildString {
    annotations.forEach { annotation ->
      append("@$annotation\n")
    }
    val parameters = inputs.joinToString(", ") { it.toString() }.orEmpty()
    append("fn $propertyName($parameters) -> ${defaultValue.name} {\n")
  }

  override fun addInput(input: FunctionInput<out ShaderType>) {
    when {
      inputs.size == 2 ->
        error("Too many parameters for function")
      expectedInputs[inputs.size] != input.defaultValue ->
        error("""Parameter type mismatch ${input.defaultValue::class.simpleName}""")
      else -> inputs.add(input)
    }
  }
}

@PublishedApi
internal class FunctionStatement3<T : ShaderType, I1 : ShaderType, I2 : ShaderType, I3 : ShaderType>(
  scope: ShaderBuilderScope,
  defaultValue: Invocable3<T, I1, I2, I3>,
  public val expectedInputs: List<ShaderType>,
  public val annotations: List<String> = emptyList(),
) : ReadOnlyPropertyBaseStatement<Invocable3<T, I1, I2, I3>>(scope, defaultValue, isFunction = true),
    FunctionWithParameters {
  private val inputs: MutableList<FunctionInput<out ShaderType>> = mutableListOf()

  override fun toString(): String = buildString {
    annotations.forEach { annotation ->
      append("@$annotation\n")
    }
    val parameters = inputs.joinToString(", ") { it.toString() }.orEmpty()
    append("fn $propertyName($parameters) -> ${defaultValue.name} {\n")
  }

  override fun addInput(input: FunctionInput<out ShaderType>) {
    when {
      inputs.size == 3 ->
        error("Too many parameters for function")
      expectedInputs[inputs.size] != input.defaultValue ->
        error("""Parameter type mismatch ${input.defaultValue::class.simpleName}""")
      else -> inputs.add(input)
    }
  }
}

@PublishedApi
internal class FunctionStatement4<T : ShaderType, I1 : ShaderType, I2 : ShaderType, I3 : ShaderType, I4 : ShaderType>(
  scope: ShaderBuilderScope,
  defaultValue: Invocable4<T, I1, I2, I3, I4>,
  public val expectedInputs: List<ShaderType>,
  public val annotations: List<String> = emptyList(),
) : ReadOnlyPropertyBaseStatement<Invocable4<T, I1, I2, I3, I4>>(scope, defaultValue, isFunction = true),
    FunctionWithParameters {
  private val inputs: MutableList<FunctionInput<out ShaderType>> = mutableListOf()

  override fun toString(): String = buildString {
    annotations.forEach { annotation ->
      append("@$annotation\n")
    }
    val parameters = inputs.joinToString(", ") { it.toString() }.orEmpty()
    append("fn $propertyName($parameters) -> ${defaultValue.name} {\n")
  }

  override fun addInput(input: FunctionInput<out ShaderType>) {
    when {
      inputs.size == 4 ->
        error("Too many parameters for function")
      expectedInputs[inputs.size] != input.defaultValue ->
        error("""Parameter type mismatch ${input.defaultValue::class.simpleName}""")
      else -> inputs.add(input)
    }
  }
}

@PublishedApi
internal class FunctionStatement5<T : ShaderType, I1 : ShaderType, I2 : ShaderType, I3 : ShaderType, I4 : ShaderType, I5 : ShaderType>(
  scope: ShaderBuilderScope,
  defaultValue: Invocable5<T, I1, I2, I3, I4, I5>,
  public val expectedInputs: List<ShaderType>,
  public val annotations: List<String> = emptyList(),
) : ReadOnlyPropertyBaseStatement<Invocable5<T, I1, I2, I3, I4, I5>>(scope, defaultValue, isFunction = true),
    FunctionWithParameters {
  private val inputs: MutableList<FunctionInput<out ShaderType>> = mutableListOf()

  override fun toString(): String = buildString {
    annotations.forEach { annotation ->
      append("@$annotation\n")
    }
    val parameters = inputs.joinToString(", ") { it.toString() }.orEmpty()
    append("fn $propertyName($parameters) -> ${defaultValue.name} {\n")
  }

  override fun addInput(input: FunctionInput<out ShaderType>) {
    when {
      inputs.size == 5 ->
        error("Too many parameters for function")
      expectedInputs[inputs.size] != input.defaultValue ->
        error("""Parameter type mismatch ${input.defaultValue::class.simpleName}""")
      else -> inputs.add(input)
    }
  }
}

@PublishedApi
internal class FunctionStatement6<T : ShaderType, I1 : ShaderType, I2 : ShaderType, I3 : ShaderType, I4 : ShaderType, I5 : ShaderType, I6 : ShaderType>(
  scope: ShaderBuilderScope,
  defaultValue: Invocable6<T, I1, I2, I3, I4, I5, I6>,
  public val expectedInputs: List<ShaderType>,
  public val annotations: List<String> = emptyList(),
) : ReadOnlyPropertyBaseStatement<Invocable6<T, I1, I2, I3, I4, I5, I6>>(scope, defaultValue, isFunction = true),
    FunctionWithParameters {
  private val inputs: MutableList<FunctionInput<out ShaderType>> = mutableListOf()

  override fun toString(): String = buildString {
    annotations.forEach { annotation ->
      append("@$annotation\n")
    }
    val parameters = inputs.joinToString(", ") { it.toString() }.orEmpty()
    append("fn $propertyName($parameters) -> ${defaultValue.name} {\n")
  }

  override fun addInput(input: FunctionInput<out ShaderType>) {
    when {
      inputs.size == 6 ->
        error("Too many parameters for function")
      expectedInputs[inputs.size] != input.defaultValue ->
        error("""Parameter type mismatch ${input.defaultValue::class.simpleName}""")
      else -> inputs.add(input)
    }
  }
}

@PublishedApi
internal class FunctionStatement7<T : ShaderType, I1 : ShaderType, I2 : ShaderType, I3 : ShaderType, I4 : ShaderType, I5 : ShaderType, I6 : ShaderType, I7 : ShaderType>(
  scope: ShaderBuilderScope,
  defaultValue: Invocable7<T, I1, I2, I3, I4, I5, I6, I7>,
  public val expectedInputs: List<ShaderType>,
  public val annotations: List<String> = emptyList(),
) : ReadOnlyPropertyBaseStatement<Invocable7<T, I1, I2, I3, I4, I5, I6, I7>>(scope, defaultValue, isFunction = true),
    FunctionWithParameters {
  private val inputs: MutableList<FunctionInput<out ShaderType>> = mutableListOf()

  override fun toString(): String = buildString {
    annotations.forEach { annotation ->
      append("@$annotation\n")
    }
    val parameters = inputs.joinToString(", ") { it.toString() }.orEmpty()
    append("fn $propertyName($parameters) -> ${defaultValue.name} {\n")
  }

  override fun addInput(input: FunctionInput<out ShaderType>) {
    when {
      inputs.size == 7 ->
        error("Too many parameters for function")
      expectedInputs[inputs.size] != input.defaultValue ->
        error("""Parameter type mismatch ${input.defaultValue::class.simpleName}""")
      else -> inputs.add(input)
    }
  }
}

@PublishedApi
internal class FunctionStatement8<T : ShaderType, I1 : ShaderType, I2 : ShaderType, I3 : ShaderType, I4 : ShaderType, I5 : ShaderType, I6 : ShaderType, I7 : ShaderType, I8 : ShaderType>(
  scope: ShaderBuilderScope,
  defaultValue: Invocable8<T, I1, I2, I3, I4, I5, I6, I7, I8>,
  public val expectedInputs: List<ShaderType>,
  public val annotations: List<String> = emptyList(),
) : ReadOnlyPropertyBaseStatement<Invocable8<T, I1, I2, I3, I4, I5, I6, I7, I8>>(scope, defaultValue, isFunction = true),
    FunctionWithParameters {
  private val inputs: MutableList<FunctionInput<out ShaderType>> = mutableListOf()

  override fun toString(): String = buildString {
    annotations.forEach { annotation ->
      append("@$annotation\n")
    }
    val parameters = inputs.joinToString(", ") { it.toString() }.orEmpty()
    append("fn $propertyName($parameters) -> ${defaultValue.name} {\n")
  }

  override fun addInput(input: FunctionInput<out ShaderType>) {
    when {
      inputs.size == 8 ->
        error("Too many parameters for function")
      expectedInputs[inputs.size] != input.defaultValue ->
        error("""Parameter type mismatch ${input.defaultValue::class.simpleName}""")
      else -> inputs.add(input)
    }
  }
}

@PublishedApi
internal class FunctionStatement9<T : ShaderType, I1 : ShaderType, I2 : ShaderType, I3 : ShaderType, I4 : ShaderType, I5 : ShaderType, I6 : ShaderType, I7 : ShaderType, I8 : ShaderType, I9 : ShaderType>(
  scope: ShaderBuilderScope,
  defaultValue: Invocable9<T, I1, I2, I3, I4, I5, I6, I7, I8, I9>,
  public val expectedInputs: List<ShaderType>,
  public val annotations: List<String> = emptyList(),
) : ReadOnlyPropertyBaseStatement<Invocable9<T, I1, I2, I3, I4, I5, I6, I7, I8, I9>>(scope, defaultValue, isFunction = true),
    FunctionWithParameters {
  private val inputs: MutableList<FunctionInput<out ShaderType>> = mutableListOf()

  override fun toString(): String = buildString {
    annotations.forEach { annotation ->
      append("@$annotation\n")
    }
    val parameters = inputs.joinToString(", ") { it.toString() }.orEmpty()
    append("fn $propertyName($parameters) -> ${defaultValue.name} {\n")
  }

  override fun addInput(input: FunctionInput<out ShaderType>) {
    when {
      inputs.size == 9 ->
        error("Too many parameters for function")
      expectedInputs[inputs.size] != input.defaultValue ->
        error("""Parameter type mismatch ${input.defaultValue::class.simpleName}""")
      else -> inputs.add(input)
    }
  }
}

@PublishedApi
internal class FunctionStatement10<T : ShaderType, I1 : ShaderType, I2 : ShaderType, I3 : ShaderType, I4 : ShaderType, I5 : ShaderType, I6 : ShaderType, I7 : ShaderType, I8 : ShaderType, I9 : ShaderType, I10 : ShaderType>(
  scope: ShaderBuilderScope,
  defaultValue: Invocable10<T, I1, I2, I3, I4, I5, I6, I7, I8, I9, I10>,
  public val expectedInputs: List<ShaderType>,
  public val annotations: List<String> = emptyList(),
) : ReadOnlyPropertyBaseStatement<Invocable10<T, I1, I2, I3, I4, I5, I6, I7, I8, I9, I10>>(scope, defaultValue, isFunction = true),
    FunctionWithParameters {
  private val inputs: MutableList<FunctionInput<out ShaderType>> = mutableListOf()

  override fun toString(): String = buildString {
    annotations.forEach { annotation ->
      append("@$annotation\n")
    }
    val parameters = inputs.joinToString(", ") { it.toString() }.orEmpty()
    append("fn $propertyName($parameters) -> ${defaultValue.name} {\n")
  }

  override fun addInput(input: FunctionInput<out ShaderType>) {
    when {
      inputs.size == 10 ->
        error("Too many parameters for function")
      expectedInputs[inputs.size] != input.defaultValue ->
        error("""Parameter type mismatch ${input.defaultValue::class.simpleName}""")
      else -> inputs.add(input)
    }
  }
}
