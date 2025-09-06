package experiment.redo

sealed interface ShaderType {
    val name: String
}

// Types scalaires
interface bool : ShaderType {
    override val name: String
        get() = "bool"
}
interface f32 : ShaderType {
    override val name: String
        get() = "f32"
}

interface f16 : ShaderType {
    override val name: String
        get() = "f16"
}
interface i32 : ShaderType {
    override val name: String
        get() = "i32"
}
interface u32 : ShaderType {
    override val name: String
        get() = "u32"
}

// Types vecteur 2D
interface vec2<T : ShaderType> : ShaderType
interface vec2f : vec2<f32> {

    override val name: String
        get() = "vec2f"
}
interface vec2h : vec2<f16>
interface vec2i : vec2<i32>
interface vec2u : vec2<u32>

// Types vecteur 3D
sealed interface vec3<T : ShaderType> : ShaderType
interface vec3f : vec3<f32> {

    operator fun times(value: f32): vec3f {
        return getDefaultValue<vec3f>()
    }

    override val name: String
        get() = "vec3f"
}
interface vec3h : vec3<f16>
interface vec3i : vec3<i32>
interface vec3u : vec3<u32>

// Types vecteur 4D
interface vec4<T : ShaderType> : ShaderType
interface vec4f : vec4<f32> {

    val xyz: vec3f
        get() = getDefaultValue<vec3f>()

    override val name: String
        get() = "vec4f"
}
interface vec4h : vec4<f16>
interface vec4i : vec4<i32>
interface vec4u : vec4<u32>

// Types matrices
interface mat2x2<T : ShaderType> : ShaderType
interface mat2x2f : mat2x2<f32>
interface mat2x2h : mat2x2<f16>

interface mat2x3<T : ShaderType> : ShaderType
interface mat2x3f : mat2x3<f32>
interface mat2x3h : mat2x3<f16>

interface mat2x4<T : ShaderType> : ShaderType
interface mat2x4f : mat2x4<f32>
interface mat2x4h : mat2x4<f16>

interface mat3x2<T : ShaderType> : ShaderType
interface mat3x2f : mat3x2<f32>
interface mat3x2h : mat3x2<f16>

interface mat3x3<T : ShaderType> : ShaderType
interface mat3x3f : mat3x3<f32>
interface mat3x3h : mat3x3<f16>

interface mat3x4<T : ShaderType> : ShaderType
interface mat3x4f : mat3x4<f32>
interface mat3x4h : mat3x4<f16>

interface mat4x2<T : ShaderType> : ShaderType
interface mat4x2f : mat4x2<f32>
interface mat4x2h : mat4x2<f16>

interface mat4x3<T : ShaderType> : ShaderType
interface mat4x3f : mat4x3<f32>
interface mat4x3h : mat4x3<f16>

interface mat4x4<T : ShaderType> : ShaderType
interface mat4x4f : mat4x4<f32>
interface mat4x4h : mat4x4<f16>

// Types de tableau
interface array<T : ShaderType> : ShaderType
interface array_sized<T : ShaderType, N : Int> : ShaderType

// Types atomiques
interface iatomic<T : ShaderType> : ShaderType where T : i32
interface uatomic<T : ShaderType> : ShaderType where T : u32

// Types de texture
interface texture_1d<T : ShaderType> : ShaderType
interface texture_2d<T : ShaderType> : ShaderType
interface texture_2d_array<T : ShaderType> : ShaderType
interface texture_3d<T : ShaderType> : ShaderType
interface texture_cube<T : ShaderType> : ShaderType
interface texture_cube_array<T : ShaderType> : ShaderType
interface texture_multisampled_2d<T : ShaderType> : ShaderType

// Types de texture de profondeur
interface texture_depth_2d : ShaderType
interface texture_depth_2d_array : ShaderType
interface texture_depth_cube : ShaderType
interface texture_depth_cube_array : ShaderType
interface texture_depth_multisampled_2d : ShaderType

// Types de texture de stockage
interface texture_storage_1d<T : ShaderType, A : ShaderType> : ShaderType
interface texture_storage_2d<T : ShaderType, A : ShaderType> : ShaderType
interface texture_storage_2d_array<T : ShaderType, A : ShaderType> : ShaderType
interface texture_storage_3d<T : ShaderType, A : ShaderType> : ShaderType

// Types de sampler
interface sampler : ShaderType
interface sampler_comparison : ShaderType

// Types de pointeur
interface ptr<S : ShaderType, T : ShaderType> : ShaderType