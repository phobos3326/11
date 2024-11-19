// Top-level build file where you can add configuration options common to all sub-projects/modules.

val koinVersion = "4.0.0"
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false

    id("com.google.devtools.ksp") version "2.0.21-1.0.25" apply false
    id ("com.google.dagger.hilt.android") version "2.52" apply false
    kotlin("jvm") version "2.0.21"


}







