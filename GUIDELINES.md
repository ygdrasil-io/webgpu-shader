# Shader DSL - Development Guidelines

This development guide is intended for developers and AI agents working on the Shader DSL project. It provides
comprehensive guidelines, standards, and best practices for contributing to and maintaining the codebase.

## Project Structure
```
shared/
├── src/
│   ├── commonGenerated/         # Generated code from gradle task
│   │   └── kotlin/              
│   ├── commonMain/
│   │   └── kotlin/              # Core implementation
│   └── commonTest/
│       └── kotlin/              # tests
├── build.gradle.kts
├── settings.gradle.kts
└── ... (build config files)
```

## Project Overview
This is a Kotlin Multiplatform DSL for generating Shader code through a type-safe builder pattern.
It use WGSL (WebGPU Shading Language) as reference language but could be extended to support other languages.

## Code Architecture

### Core Concepts
- **Statement-based architecture**: All shader elements inherit from `BaseStatement`
- **Scope management**: `ShaderBuilderScope` tracks partial and complete statements
- **Type safety**: Kotlin types map to WGSL types (e.g., `vec4f`, `f32`, `i32`, `bool`)

### Statement Types
1. **Partial Statements** (accumulate in `partialStatements`):
   - `PropertyStatement`: Property access (e.g., `.xyz`)
   - `VariableStatement`: Variable references
   - `OperatorStatement`: Math operators (+, -, *, /)
   - `ConstantStatement`: Literal values

2. **Complete Statements** (added to `statements`):
   - Function declarations (`fn`)
   - Vertex/Fragment shaders
   - Uniform/Variable declarations
   - Return statements

## Coding Standards

### Naming Conventions
- **Files**: lowercase with hyphens (e.g., `function-body.kt`, `function-input.kt`)
- **Classes**: PascalCase (e.g., `BaseStatement`, `ShaderBuilderScope`)
- **Internal classes**: Mark implementation classes as `internal`
- **Functions**: camelCase for DSL builders (e.g., `shader`, `vertex`)

### Package Structure
- All code in `experiment.redo` package
- Related functionality grouped in single files
- Separate files for major concepts (function, block, statement, etc.)

### DSL Design Patterns
1. **Property delegation**: Use `by` for declarative syntax
   ```kotlin
   val uniform by uniform<f32>(binding = 0, group = 0)
   val pos by input<vec4f>()
   ```

2. **Lambda receivers**: Scope-based builders
   ```kotlin
   shader { /* ShaderBuilderScope */ }
   fn { /* function scope */ }
   ```

3. **Type parameters**: Generic types for WGSL types
   ```kotlin
   fn<ReturnType, InputType> { }
   uniform<f32>()
   ```

### Code Organization
- Keep statement classes together in `statement.kt`
- Separate DSL entry points in `dsl.kt`
- Group related builders (e.g., `function.kt`, `function-body.kt`, `function-input.kt`)

### Testing
- Test files in `commonTest/kotlin/`
- Use descriptive test names
- Include expected WGSL output for verification
- Test format: Given/When/Then structure

#### Running Tests
- **Full test suite** (all platforms): `./gradlew check`
- **Fast iteration** (JVM only): `./gradlew jvmTest`
  - Use JVM tests during development for quick feedback
  - Run full suite before committing/pushing changes

#### Understanding Test Results
- **Cache behavior**: If tests show `FROM-CACHE` or `UP-TO-DATE`, it means the code hasn't been modified since last run
- **Test reports location**: `./shared/build/reports/tests/`
  - `jvmTest/` - JVM test results (HTML)
  - `wasmJsTest/` - WebAssembly test results (HTML)
  - `iosSimulatorArm64Test/` - iOS simulator test results (HTML)
  - `allTests/` - Aggregated results from all platforms (HTML)
- **LLM-friendly format**: `./shared/build/test-results/{platform}/TEST-*.xml`
  - JUnit XML format (concise, structured)
  - Contains test suite summary, test cases, and failure details
  - Much easier to parse than HTML reports
  - Example: `cat ./shared/build/test-results/jvmTest/TEST-*.xml`
- **Analyzing results**: Don't hesitate to scan the report directories yourself to be exhaustive
  - For quick analysis: read XML files directly
  - For detailed view: open `index.html` in report directories
  - Check `classes/` folder for individual test class reports

### Implementation Rules
1. **Always extend `BaseStatement`** for new shader elements
2. **Call `scope.push(this)`** in statement constructors
3. **Override `toString()`** to generate WGSL code
4. **Use `internal`** for implementation classes
5. **Expose public DSL functions** for user-facing API

### WGSL Output Format
- Follow WGSL specification syntax
- Include proper annotations (@vertex, @builtin, @group, @binding)
- Maintain consistent indentation in generated code
- Add blank lines between top-level declarations

## Common Patterns

### Adding New Statement Types
```kotlin
internal class NewStatement(
    scope: ShaderBuilderScope,
    // parameters
) : BaseStatement(scope) {
    override fun toString(): String {
        return "WGSL output"
    }
}
```

### Adding New DSL Builders
```kotlin
context(scope: ShaderBuilderScope)
fun newBuilder(params): ReturnType {
    return NewStatement(this, params)
}
```

## Refactoring Guidelines
- Maintain backward compatibility for public DSL API
- Keep statement classes focused on single responsibility
- Extract common patterns into base classes or utilities
- Document breaking changes in commit messages

## Commit Message Format
Follow conventional commits:
- `feat:` for new features
- `refactor:` for code restructuring
- `fix:` for bug fixes
- Include affected component (e.g., "add `OperatorStatement` for math operations")
