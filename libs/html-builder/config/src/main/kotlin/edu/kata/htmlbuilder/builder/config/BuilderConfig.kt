package edu.kata.htmlbuilder.builder.config

import edu.kata.htmlbuilder.builder.core.usecase.BuildHtmlUseCaseImpl
import edu.kata.htmlbuilder.builder.driver.usecase.BuildHtmlUseCase
import org.koin.dsl.module

val builderModule = module {
    single<BuildHtmlUseCase> { BuildHtmlUseCaseImpl() }
}