/* This is a generated file. Do not edit! */

package experiment.redo

import kotlin.Unit
import kotlin.jvm.JvmName

context(scope: ShaderBuilderScope)
@JvmName("fn0")
public inline fun <reified T : ShaderType> fn(block: ShaderFunctionBuilderScope.() -> Unit): ReadOnlyPropertyBaseStatement<Invocable0<T>> {
  val defaultValue = getDefaultValue<T>()

  val statement = FunctionStatement0(
    scope,
    Invocable0Impl<T>(defaultValue),
    emptyList()
  ).addToScope()

  ShaderFunctionBuilderScopeImpl(scope, statement).block()

  // Add end block statement to stack
  EndBlockStatement(scope).addToScope()
  return statement
}

context(scope: ShaderBuilderScope)
@JvmName("fn1")
public inline fun <reified T : ShaderType, reified I1 : ShaderType> fn(block: ShaderFunctionBuilderScope.() -> Unit): ReadOnlyPropertyBaseStatement<Invocable1<T, I1>> {
  val defaultValue = getDefaultValue<T>()
  val i1 = getDefaultValue<I1>()

  val statement = FunctionStatement1(
    scope,
    Invocable1Impl<T, I1>(defaultValue),
    listOf(i1)
  ).addToScope()

  ShaderFunctionBuilderScopeImpl(scope, statement).block()

  // Add end block statement to stack
  EndBlockStatement(scope).addToScope()
  return statement
}

context(scope: ShaderBuilderScope)
@JvmName("fn2")
public inline fun <reified T : ShaderType, reified I1 : ShaderType, reified I2 : ShaderType> fn(block: ShaderFunctionBuilderScope.() -> Unit): ReadOnlyPropertyBaseStatement<Invocable2<T, I1, I2>> {
  val defaultValue = getDefaultValue<T>()
  val i1 = getDefaultValue<I1>()
  val i2 = getDefaultValue<I2>()

  val statement = FunctionStatement2(
    scope,
    Invocable2Impl<T, I1, I2>(defaultValue),
    listOf(i1, i2)
  ).addToScope()

  ShaderFunctionBuilderScopeImpl(scope, statement).block()

  // Add end block statement to stack
  EndBlockStatement(scope).addToScope()
  return statement
}

context(scope: ShaderBuilderScope)
@JvmName("fn3")
public inline fun <reified T : ShaderType, reified I1 : ShaderType, reified I2 : ShaderType, reified I3 : ShaderType> fn(block: ShaderFunctionBuilderScope.() -> Unit): ReadOnlyPropertyBaseStatement<Invocable3<T, I1, I2, I3>> {
  val defaultValue = getDefaultValue<T>()
  val i1 = getDefaultValue<I1>()
  val i2 = getDefaultValue<I2>()
  val i3 = getDefaultValue<I3>()

  val statement = FunctionStatement3(
    scope,
    Invocable3Impl<T, I1, I2, I3>(defaultValue),
    listOf(i1, i2, i3)
  ).addToScope()

  ShaderFunctionBuilderScopeImpl(scope, statement).block()

  // Add end block statement to stack
  EndBlockStatement(scope).addToScope()
  return statement
}

context(scope: ShaderBuilderScope)
@JvmName("fn4")
public inline fun <reified T : ShaderType, reified I1 : ShaderType, reified I2 : ShaderType, reified I3 : ShaderType, reified I4 : ShaderType> fn(block: ShaderFunctionBuilderScope.() -> Unit): ReadOnlyPropertyBaseStatement<Invocable4<T, I1, I2, I3, I4>> {
  val defaultValue = getDefaultValue<T>()
  val i1 = getDefaultValue<I1>()
  val i2 = getDefaultValue<I2>()
  val i3 = getDefaultValue<I3>()
  val i4 = getDefaultValue<I4>()

  val statement = FunctionStatement4(
    scope,
    Invocable4Impl<T, I1, I2, I3, I4>(defaultValue),
    listOf(i1, i2, i3, i4)
  ).addToScope()

  ShaderFunctionBuilderScopeImpl(scope, statement).block()

  // Add end block statement to stack
  EndBlockStatement(scope).addToScope()
  return statement
}

context(scope: ShaderBuilderScope)
@JvmName("fn5")
public inline fun <reified T : ShaderType, reified I1 : ShaderType, reified I2 : ShaderType, reified I3 : ShaderType, reified I4 : ShaderType, reified I5 : ShaderType> fn(block: ShaderFunctionBuilderScope.() -> Unit): ReadOnlyPropertyBaseStatement<Invocable5<T, I1, I2, I3, I4, I5>> {
  val defaultValue = getDefaultValue<T>()
  val i1 = getDefaultValue<I1>()
  val i2 = getDefaultValue<I2>()
  val i3 = getDefaultValue<I3>()
  val i4 = getDefaultValue<I4>()
  val i5 = getDefaultValue<I5>()

  val statement = FunctionStatement5(
    scope,
    Invocable5Impl<T, I1, I2, I3, I4, I5>(defaultValue),
    listOf(i1, i2, i3, i4, i5)
  ).addToScope()

  ShaderFunctionBuilderScopeImpl(scope, statement).block()

  // Add end block statement to stack
  EndBlockStatement(scope).addToScope()
  return statement
}

context(scope: ShaderBuilderScope)
@JvmName("fn6")
public inline fun <reified T : ShaderType, reified I1 : ShaderType, reified I2 : ShaderType, reified I3 : ShaderType, reified I4 : ShaderType, reified I5 : ShaderType, reified I6 : ShaderType> fn(block: ShaderFunctionBuilderScope.() -> Unit): ReadOnlyPropertyBaseStatement<Invocable6<T, I1, I2, I3, I4, I5, I6>> {
  val defaultValue = getDefaultValue<T>()
  val i1 = getDefaultValue<I1>()
  val i2 = getDefaultValue<I2>()
  val i3 = getDefaultValue<I3>()
  val i4 = getDefaultValue<I4>()
  val i5 = getDefaultValue<I5>()
  val i6 = getDefaultValue<I6>()

  val statement = FunctionStatement6(
    scope,
    Invocable6Impl<T, I1, I2, I3, I4, I5, I6>(defaultValue),
    listOf(i1, i2, i3, i4, i5, i6)
  ).addToScope()

  ShaderFunctionBuilderScopeImpl(scope, statement).block()

  // Add end block statement to stack
  EndBlockStatement(scope).addToScope()
  return statement
}

context(scope: ShaderBuilderScope)
@JvmName("fn7")
public inline fun <reified T : ShaderType, reified I1 : ShaderType, reified I2 : ShaderType, reified I3 : ShaderType, reified I4 : ShaderType, reified I5 : ShaderType, reified I6 : ShaderType, reified I7 : ShaderType> fn(block: ShaderFunctionBuilderScope.() -> Unit): ReadOnlyPropertyBaseStatement<Invocable7<T, I1, I2, I3, I4, I5, I6, I7>> {
  val defaultValue = getDefaultValue<T>()
  val i1 = getDefaultValue<I1>()
  val i2 = getDefaultValue<I2>()
  val i3 = getDefaultValue<I3>()
  val i4 = getDefaultValue<I4>()
  val i5 = getDefaultValue<I5>()
  val i6 = getDefaultValue<I6>()
  val i7 = getDefaultValue<I7>()

  val statement = FunctionStatement7(
    scope,
    Invocable7Impl<T, I1, I2, I3, I4, I5, I6, I7>(defaultValue),
    listOf(i1, i2, i3, i4, i5, i6, i7)
  ).addToScope()

  ShaderFunctionBuilderScopeImpl(scope, statement).block()

  // Add end block statement to stack
  EndBlockStatement(scope).addToScope()
  return statement
}

context(scope: ShaderBuilderScope)
@JvmName("fn8")
public inline fun <reified T : ShaderType, reified I1 : ShaderType, reified I2 : ShaderType, reified I3 : ShaderType, reified I4 : ShaderType, reified I5 : ShaderType, reified I6 : ShaderType, reified I7 : ShaderType, reified I8 : ShaderType> fn(block: ShaderFunctionBuilderScope.() -> Unit): ReadOnlyPropertyBaseStatement<Invocable8<T, I1, I2, I3, I4, I5, I6, I7, I8>> {
  val defaultValue = getDefaultValue<T>()
  val i1 = getDefaultValue<I1>()
  val i2 = getDefaultValue<I2>()
  val i3 = getDefaultValue<I3>()
  val i4 = getDefaultValue<I4>()
  val i5 = getDefaultValue<I5>()
  val i6 = getDefaultValue<I6>()
  val i7 = getDefaultValue<I7>()
  val i8 = getDefaultValue<I8>()

  val statement = FunctionStatement8(
    scope,
    Invocable8Impl<T, I1, I2, I3, I4, I5, I6, I7, I8>(defaultValue),
    listOf(i1, i2, i3, i4, i5, i6, i7, i8)
  ).addToScope()

  ShaderFunctionBuilderScopeImpl(scope, statement).block()

  // Add end block statement to stack
  EndBlockStatement(scope).addToScope()
  return statement
}

context(scope: ShaderBuilderScope)
@JvmName("fn9")
public inline fun <reified T : ShaderType, reified I1 : ShaderType, reified I2 : ShaderType, reified I3 : ShaderType, reified I4 : ShaderType, reified I5 : ShaderType, reified I6 : ShaderType, reified I7 : ShaderType, reified I8 : ShaderType, reified I9 : ShaderType> fn(block: ShaderFunctionBuilderScope.() -> Unit): ReadOnlyPropertyBaseStatement<Invocable9<T, I1, I2, I3, I4, I5, I6, I7, I8, I9>> {
  val defaultValue = getDefaultValue<T>()
  val i1 = getDefaultValue<I1>()
  val i2 = getDefaultValue<I2>()
  val i3 = getDefaultValue<I3>()
  val i4 = getDefaultValue<I4>()
  val i5 = getDefaultValue<I5>()
  val i6 = getDefaultValue<I6>()
  val i7 = getDefaultValue<I7>()
  val i8 = getDefaultValue<I8>()
  val i9 = getDefaultValue<I9>()

  val statement = FunctionStatement9(
    scope,
    Invocable9Impl<T, I1, I2, I3, I4, I5, I6, I7, I8, I9>(defaultValue),
    listOf(i1, i2, i3, i4, i5, i6, i7, i8, i9)
  ).addToScope()

  ShaderFunctionBuilderScopeImpl(scope, statement).block()

  // Add end block statement to stack
  EndBlockStatement(scope).addToScope()
  return statement
}

context(scope: ShaderBuilderScope)
@JvmName("fn10")
public inline fun <reified T : ShaderType, reified I1 : ShaderType, reified I2 : ShaderType, reified I3 : ShaderType, reified I4 : ShaderType, reified I5 : ShaderType, reified I6 : ShaderType, reified I7 : ShaderType, reified I8 : ShaderType, reified I9 : ShaderType, reified I10 : ShaderType> fn(block: ShaderFunctionBuilderScope.() -> Unit): ReadOnlyPropertyBaseStatement<Invocable10<T, I1, I2, I3, I4, I5, I6, I7, I8, I9, I10>> {
  val defaultValue = getDefaultValue<T>()
  val i1 = getDefaultValue<I1>()
  val i2 = getDefaultValue<I2>()
  val i3 = getDefaultValue<I3>()
  val i4 = getDefaultValue<I4>()
  val i5 = getDefaultValue<I5>()
  val i6 = getDefaultValue<I6>()
  val i7 = getDefaultValue<I7>()
  val i8 = getDefaultValue<I8>()
  val i9 = getDefaultValue<I9>()
  val i10 = getDefaultValue<I10>()

  val statement = FunctionStatement10(
    scope,
    Invocable10Impl<T, I1, I2, I3, I4, I5, I6, I7, I8, I9, I10>(defaultValue),
    listOf(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10)
  ).addToScope()

  ShaderFunctionBuilderScopeImpl(scope, statement).block()

  // Add end block statement to stack
  EndBlockStatement(scope).addToScope()
  return statement
}
