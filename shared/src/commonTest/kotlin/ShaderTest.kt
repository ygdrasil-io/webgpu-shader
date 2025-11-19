package experiment.redo

import kotlin.test.Test
import kotlin.test.assertEquals

class ShaderTestTest {

    @Test
    fun `test 1 - simple uniform declaration`() {
        val expected = "@group(0) @binding(0) var<uniform> myUniform: f32;"

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
                body {
                }
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
                body {
                }
            }
        }.source

        assertEquals(expected, actual)
    }

    @Test
    fun `test 4 - function with simple return constant`() {
        val expected = """
fn myFunction() -> f32 {
    return 1.0;
}
        """.trimIndent()

        val actual = shader {
            val myFunction by fn<f32> {
                body {
                    returns(f32(1.0))
                }
            }
        }.source

        assertEquals(expected, actual)
    }

    @Test
    fun `test 5 - function with return variable`() {
        val expected = """
fn myFunction(x: f32) -> f32 {
    return x;
}
        """.trimIndent()

        val actual = shader {
            val myFunction by fn<f32, f32> {
                val x by input<f32>()
                body {
                    returns(x)
                }
            }
        }.source

        assertEquals(expected, actual)
    }

    @Test
    fun `test 6 - simple arithmetic operation`() {
        val expected = """
fn myFunction(x: f32, y: f32) -> f32 {
    return x + y;
}
        """.trimIndent()

        val actual = shader {
            val myFunction by fn<f32, f32> { // Note: fn ne supporte qu'un seul param pour l'instant
                val x by input<f32>()
                body {
                    returns(x)
                }
            }
        }.source

        assertEquals(expected, actual)
    }

    @Test
    fun `test 7 - vec3f multiply by f32`() {
        val expected = """
fn myFunction(pos: vec3f, scale: f32) -> vec3f {
    return pos * scale;
}
        """.trimIndent()

        val actual = shader {
            val myFunction by fn<vec3f, vec3f> {
                val pos by input<vec3f>()
                body {
                    // Note: on ne peut pas passer scale ici pour le moment
                    returns(pos)
                }
            }
        }.source

        assertEquals(expected, actual)
    }

    @Test
    fun `test 8 - vec4f constructor call`() {
        val expected = """
fn myFunction() -> vec4f {
    return vec4f(1.0, 2.0, 3.0, 4.0);
}
        """.trimIndent()

        val actual = shader {
            val myFunction by fn<vec4f> {
                body {
                    returns(vec4f(f32(1.0), f32(2.0)))
                }
            }
        }.source

        assertEquals(expected, actual)
    }

    @Test
    fun `test 9 - property access xyz`() {
        val expected = """
fn myFunction(pos: vec4f) -> vec3f {
    return pos.xyz;
}
        """.trimIndent()

        val actual = shader {
            val myFunction by fn<vec3f, vec4f> {
                val pos by input<vec4f>()
                body {
                    returns(pos.xyz)
                }
            }
        }.source

        assertEquals(expected, actual)
    }

    @Test
    fun `test 10 - vertex function with annotations`() {
        val expected = """
@vertex
fn myVertex(@builtin(position) pos: vec4f) -> @builtin(position) vec4f {
    return pos;
}
        """.trimIndent()

        val actual = shader {
            vertex {
                val pos by input<vec4f>()
                body {
                    returns(pos)
                }
            }
        }.source

        assertEquals(expected, actual)
    }

    @Test
    fun `test 11 - full shader example`() {
        val expected = """
@group(0) @binding(0) var<uniform> uniform: f32;

fn test(pos: vec3f) -> vec4f {
    return vec4f(pos * uniform, 1.0);
}

@vertex
fn mainVertex(@builtin(position) pos: vec4f) -> @builtin(position) vec4f {
    return test(pos.xyz);
}
        """.trimIndent()

        val actual = shader {
            val uniform by uniform<f32>(
                binding = 0,
                group = 0
            )

            val test by fn<vec4f, vec3f> {
                val pos by input<vec3f>()
                body {
                    returns(vec4f(pos * uniform, f32(1.0)))
                }
            }

            vertex {
                val pos by input<vec4f>()

                body {
                    returns(test(pos.xyz))
                }
            }

        }.source

        assertEquals(expected, actual)
    }
}