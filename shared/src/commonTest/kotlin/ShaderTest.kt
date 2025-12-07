@file:Suppress("UNUSED_VARIABLE", "unused")

package experiment.redo.test

import experiment.redo.ReadOnlyPropertyBaseStatement
import experiment.redo.ShaderFunctionBuilderScope
import experiment.redo.f32
import experiment.redo.fn
import experiment.redo.input
import experiment.redo.position
import experiment.redo.returning
import experiment.redo.shader
import experiment.redo.uniform
import experiment.redo.vec3f
import experiment.redo.vec4f
import experiment.redo.vertex
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty
import kotlin.test.Test
import kotlin.test.assertEquals

class ShaderTest {

    val tab = "\t"

    @Test
    fun `test 1 - simple uniform declaration`() {
        val expected = """
            @group(0) @binding(0) var<uniform> myUniform: f32;
            
        """.trimIndent()

        val actual = shader {
            val myUniform by uniform<f32>(binding = 0, group = 0)
        }.source

        assertEquals(expected, actual)
    }

    @Test
    fun `test 2 - function with no parameters`() {
        val expected = """
            fn myFunction() -> f32 {
            }
            
        """.trimIndent()

        val actual = shader {
            val myFunction by fn<f32> {

            }
        }.source

        assertEquals(expected, actual)
    }

    @Test
    fun `test 3 - function with one parameter`() {
        val expected = """
            fn myFunction(x: f32) -> f32 {
            }
            
        """.trimIndent()

        val actual = shader {
            val myFunction by fn<f32, f32> {
                val x by input<f32>()

            }
        }.source

        assertEquals(expected, actual)
    }

    @Test
    fun `test 4 - function with simple return constant`() {
        val expected = """
            fn myFunction() -> f32 {
            ${tab}return 1.0;
            }
            
        """.trimIndent()

        val actual = shader {
            val myFunction by fn<f32> {

                returning(f32(1.0))
            }
        }.source

        assertEquals(expected, actual)
    }

    @Test
    fun `test 5 - function with return variable`() {
        val expected = """
            fn myFunction(x: f32) -> f32 {
            ${tab}return x;
            }
            
        """.trimIndent()

        val actual = shader {
            val myFunction by fn<f32, f32> {
                val x by input<f32>()

                returning(x)
            }
        }.source

        assertEquals(expected, actual)
    }

    @Test
    fun `test 6 - simple arithmetic operation`() {
        val expected = """
            fn myFunction(x: f32, y: f32) -> f32 {
            ${tab}return x + y;
            }
            
        """.trimIndent()

        val actual = shader {
            val myFunction by fn<f32, f32, f32> {
                val x by input<f32>()
                val y by input<f32>()

                returning(x + y)
            }
        }.source

        assertEquals(expected, actual)
    }

    @Test
    fun `test 7 - vec3f multiply by f32`() {
        val expected = """
            fn myFunction(pos: vec3f, scale: f32) -> vec3f {
            ${tab}return pos * scale;
            }
            
        """.trimIndent()

        val actual = shader {
            val myFunction by fn<vec3f, vec3f, f32> {
                val pos by input<vec3f>()
                val scale by input<f32>()

                returning(pos * scale)
            }
        }.source

        assertEquals(expected, actual)
    }

    @Test
    fun `test 8 - vec4f constructor call`() {
        val expected = """
            fn myFunction() -> vec4f {
            ${tab}return vec4f(1.0, 2.0, 3.0, 4.0);
            }
            
        """.trimIndent()

        val actual = shader {
            val myFunction by fn<vec4f> {

                returning(vec4f(f32(1.0), f32(2.0), f32(3.0), f32(4.0)))
            }
        }.source

        assertEquals(expected, actual)
    }

    @Test
    fun `test 9 - property access xyz`() {
        val expected = """
            fn myFunction(pos: vec4f) -> vec3f {
            ${tab}return pos.xyz;
            }
            
        """.trimIndent()

        val actual = shader {
            val myFunction by fn<vec3f, vec4f> {
                val pos by input<vec4f>()

                returning(pos.xyz)
            }
        }.source

        assertEquals(expected, actual)
    }

    @Test
    fun `test 10 - vertex function with annotations`() {
        val expected = """
            @vertex
            fn myVertex(@builtin(position) pos: vec4f) -> @builtin(position) vec4f {
            ${tab}return pos;
            }
            
        """.trimIndent()

        val actual = shader {
            vertex("myVertex") {
                val pos by position<vec4f>()

                returning(pos)
            }
        }.source

        assertEquals(expected, actual)
    }

    @Test
    fun `test 11 - full shader example`() {
        val expected = """
            @group(0) @binding(0) var<uniform> uniform: f32;
            fn test(pos: vec3f) -> vec4f {
            ${tab}return vec4f(pos * uniform, 1.0);
            }
            @vertex
            fn mainVertex(@builtin(position) pos: vec4f) -> @builtin(position) vec4f {
            ${tab}return test(pos.xyz);
            }
            
        """.trimIndent()

        val actual = shader {
            val uniform by uniform<f32>(
                binding = 0,
                group = 0
            )

            val test by fn<vec4f, vec3f> {
                val pos by input<vec3f>()

                returning(vec4f(pos * uniform, f32(1.0)))
            }

            vertex {
                val pos by position<vec4f>()

                returning(
                    test(pos.xyz)
                )
            }

        }.source

        assertEquals(expected, actual)
    }

    @Test
    fun `test 12 - function with multiple operations in body`() {
        val expected = """
            fn calculate(x: f32, y: f32) -> f32 {
            ${tab}let a = x + y;
            ${tab}let b = a * 2.0;
            ${tab}let c = b - x;
            ${tab}return c / y;
            }

        """.trimIndent()

        fun ShaderFunctionBuilderScope.addOperations(x: f32, y: f32): f32 {
            return x + y
        }

        val actual = shader {
            val calculate by fn<f32, f32, f32> {
                val x by input<f32>()
                val y by input<f32>()

                val result = addOperations(x, y)

                returning(result)
            }
        }.source

        assertEquals(expected, actual)
    }
}