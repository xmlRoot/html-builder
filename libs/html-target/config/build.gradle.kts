dependencies {
    implementation(driver(htmlTarget))
    implementation(core(htmlTarget))
    implementation(output(htmlTarget))

    implementation(libs.kotlin.koin)
}