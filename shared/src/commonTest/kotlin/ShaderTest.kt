package experiment.redo

import kotlin.test.Test
import kotlin.test.assertEquals

class ShaderTestTest {

    @Test
    fun example() {
        // Given
        val exceptedSource = """
@group(0) @binding(0) var<uniform> uniform: f32;

fn test(pos: vec3f) -> vec4f {
    return vec4f(pos * uniform, 1.0);
}

@vertex
fn mainVertex(@builtin(position) pos: vec4f) -> @builtin(position) vec4f {
    return test(pos.xyz);
}
        """.trimIndent()
        // When
        val source = shader {
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

        //Then
        assertEquals(exceptedSource, source)
    }
}