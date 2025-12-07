package experiment.redo
import kotlin.reflect.KProperty

sealed class ReadOnlyPropertyBaseStatement<T>(
    scope: ShaderBuilderScope,
    val defaultValue: T,
    val isFunction: Boolean = false
) : BaseStatement(scope) {
    var propertyName: String? = null
        internal set

    operator fun provideDelegate(thisRef: Any?, property: KProperty<*>): ReadOnlyPropertyBaseStatement<T> {
        init(property)
        return this
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        if (!isFunction) {
            VariableStatement(scope, propertyName!!)
                .addToScope()
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
                 .addToScope()
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
internal inline fun <reified T : ShaderType> getDefaultValue(): T = (typeInstance[T::class] as T?)
    ?: error("Type not supported: ${T::class}")

@PublishedApi
internal val typeInstance = mapOf(
    f32::class to object : f32 {},
    f16::class to object : f16 {},
    bool::class to object : bool {},
    vec4f::class to object : vec4f {},
    vec3f::class to object : vec3f {},
    vec2f::class to object : vec2f {}
)