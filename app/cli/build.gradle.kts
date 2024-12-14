plugins {
    id("kotlin-kapt")
}

dependencies {
    implementation(common())
    implementation(project(":app:base"))
    implementation(config(inputSources))
    implementation(config(htmlTarget))
    implementation(config(htmlBuilder))
    implementation(libs.picocli.core)
    kapt(libs.picocli.codegen)
    implementation(libs.kotlin.koin)
}

kapt {
    arguments {
        arg("project", "${project.group}/${project.name}")
    }
}