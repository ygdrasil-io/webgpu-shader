import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {

    alias(libs.plugins.kotlinMultiplatform)
    //alias(libs.plugins.androidLibrary)
}

kotlin {
    /*androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }*/

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    jvm()

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs()

    sourceSets {
        commonMain {
            kotlin.srcDir(
                layout.projectDirectory.dir("src").dir("commonGenerated").dir("kotlin")
            )
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
            compileOnly("org.jetbrains:annotations:26.0.2-1")
        }
    }

    compilerOptions {
        freeCompilerArgs.add("-Xcontext-parameters")
    }
}

/*  android {
    namespace = "io.ygdrasil.test_library.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}*/

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(22))
    }
}