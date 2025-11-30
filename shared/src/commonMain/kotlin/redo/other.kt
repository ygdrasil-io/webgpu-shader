package experiment.redo
import kotlin.reflect.KProperty

sealed class ReadOnlyPropertyBaseStatement<T>(
    scope: ShaderBuilderScope,
    val defaultValue: T,
    val isFunction: Boolean = false
) : BaseStatement(scope) {
    var propertyName: String? = null
        private set

    operator fun provideDelegate(thisRef: Any?, property: KProperty<*>): ReadOnlyPropertyBaseStatement<T> {
        init(property)
        return this
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        if (!isFunction) {
            VariableStatement(scope, propertyName!!)
        }
        return defaultValue
    }

    protected fun init(property: KProperty<*>) {
        // Already initialized
        if (propertyName != null) return
        propertyName = property.name
    }
}

sealed class PropertyBaseStatement<T>(
     scope: ShaderBuilderScope,
     val defaultValue: T,
     val isFunction: Boolean = false
 ) : BaseStatement(scope) {
     var propertyName: String? = null
         private set

     operator fun provideDelegate(thisRef: Any?, property: KProperty<*>): PropertyBaseStatement<T> {
         init(property)
         return this
     }

     operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
         if (!isFunction) {
             VariableStatement(scope, propertyName!!)
         }
         return defaultValue
     }

     protected fun init(property: KProperty<*>) {
         // Already initialized
         if (propertyName != null) return
         propertyName = property.name
     }

     operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {

     }
}

@PublishedApi
internal inline fun <reified T : ShaderType> getDefaultValue(): T = when (T::class) {
    f32::class -> object : f32 {}
    f16::class -> object : f16 {}
    bool::class -> object : bool {}
    vec4f::class -> object : vec4f { }
    vec3f::class -> object : vec3f { }
    vec2f::class -> object : vec2f { }
    else -> throw IllegalArgumentException("Type not supported: ${T::class}")
} as T