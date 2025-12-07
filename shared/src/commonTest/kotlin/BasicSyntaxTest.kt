@file:Suppress("UNUSED_VARIABLE", "unused")

package experiment.redo.test

import experiment.redo.ShaderFunctionBuilderScope
import experiment.redo.f32
import experiment.redo.fn
import experiment.redo.input
import experiment.redo.local
import experiment.redo.position
import experiment.redo.returning
import experiment.redo.shader
import experiment.redo.uniform
import experiment.redo.vec3f
import experiment.redo.vec4f
import experiment.redo.vertex
import org.intellij.lang.annotations.Language
import kotlin.test.Test
import kotlin.test.assertEquals

class BasicSyntaxTest {

    @Test
    fun `test 1 - simple uniform declaration`() {
        @Language("WGSL")
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
        @Language("WGSL")
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
        @Language("WGSL")
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
        @Language("WGSL")
        val expected = """
            fn myFunction() -> f32 {
                return 1.0;
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
        @Language("WGSL")
        val expected = """
            fn myFunction(x: f32) -> f32 {
                return x;
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
        @Language("WGSL")
        val expected = """
            fn myFunction(x: f32, y: f32) -> f32 {
                return x + y;
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
        @Language("WGSL")
        val expected = """
            fn myFunction(pos: vec3f, scale: f32) -> vec3f {
                return pos * scale;
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
        @Language("WGSL")
        val expected = """
            fn myFunction() -> vec4f {
                return vec4f(1.0, 2.0, 3.0, 4.0);
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
        @Language("WGSL")
        val expected = """
            fn myFunction(pos: vec4f) -> vec3f {
                return pos.xyz;
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
        @Language("WGSL")
        val expected = """
            @vertex
            fn myVertex(@builtin(position) pos: vec4f) -> @builtin(position) vec4f {
                return pos;
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
    fun `test 11 - local variable`() {
        @Language("WGSL")
        val expected = """
            fn createVector() -> vec4f {
                let x: f32 = 10.0;
                var y: f32;
                y = 15.0;
                return vec4f(x, y, 0.0, 1.0);
            }
            
        """.trimIndent()


        val actual = shader {
            val createVector by fn<vec4f> {
                val x by local<f32>(f32(10.0))
                var y by local<f32>()
                y = f32(15.0)
                returning(vec4f(x, y, f32(0.0), f32(1.0)))
            }
        }.source

        assertEquals(expected, actual)
    }

    @Test
    fun `test 12 - full shader example`() {
        @Language("WGSL")
        val expected = """
            @group(0) @binding(0) var<uniform> myUniform: f32;
            fn test(pos: vec3f) -> vec4f {
                return vec4f(pos * myUniform, 1.0);
            }
            @vertex
            fn mainVertex(@builtin(position) pos: vec4f) -> @builtin(position) vec4f {
                return test(pos.xyz);
            }
            
        """.trimIndent()

        val actual = shader {
            val myUniform by uniform<f32>(
                binding = 0,
                group = 0
            )

            val test by fn<vec4f, vec3f> {
                val pos by input<vec3f>()

                returning(vec4f(pos * myUniform, f32(1.0)))
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

}