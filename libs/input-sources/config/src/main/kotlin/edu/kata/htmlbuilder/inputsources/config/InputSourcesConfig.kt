package edu.kata.htmlbuilder.inputsources.config

import edu.kata.htmlbuilder.inputsource.driver.usecase.ReadContentInputUseCase
import edu.kata.htmlbuilder.inputsources.core.file.usecase.ReadContentInputUseCaseImpl
import org.koin.dsl.module

val inputSourcesModules = module {
    single<ReadContentInputUseCase> { ReadContentInputUseCaseImpl() }
}