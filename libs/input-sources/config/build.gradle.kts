dependencies {
    implementation(libs.kotlin.koin)
    implementation(core(inputSources))
    api(driver(inputSources))
}