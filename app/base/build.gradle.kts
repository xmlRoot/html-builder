dependencies {
    implementation(libs.kotlin.koin)
    implementation(common())
    implementation(driver(inputSources))
    implementation(driver(htmlTarget))
    implementation(driver(htmlBuilder))
    implementation(libs.logging.logback)
}