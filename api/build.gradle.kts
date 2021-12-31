plugins {
    id("discipline.base-conventions")
}

dependencies {
    api(platform(libs.adventureBom))

    compileOnlyApi(libs.adventureApi)
    compileOnlyApi(libs.adventureTextSerializerPlain)
    compileOnlyApi(libs.adventureTextSerializerLegacy)
    compileOnlyApi(libs.adventureTextSerializerGson) {
        exclude("com.google.code.gson")
    }

    api(libs.minimessage) {
        isTransitive = false
    }

    compileOnlyApi(libs.checkerQual)
}
