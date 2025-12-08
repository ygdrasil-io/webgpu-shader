plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.kotlinpoet)
}


kotlin {
    compilerOptions {
        freeCompilerArgs.add("-Xcontext-parameters")
    }
}