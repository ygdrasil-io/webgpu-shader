package experiment.redo

import kotlin.test.Test
import kotlin.test.assertEquals

class SharedCommonTest {

    @Test
    fun example() {
        // Given
        val exceptedSource = """
@group(0) @binding(0) var<uniform> inputValue: f32;

@vertex
fn main(@builtin(position) pos: vec4f) -> @builtin(position) vec4f {
    return vec4f(pos.xyz * inputValue, 1.0);
}
        """.trimIndent()
        // When
        val source = shader {
            val uniform by uniform<f32>(
                binding = 0,
                group = 0
            )

            val main by fn<vec4f> {

            }

        }.source

        //Then
        assertEquals(source, exceptedSource)
    }
}