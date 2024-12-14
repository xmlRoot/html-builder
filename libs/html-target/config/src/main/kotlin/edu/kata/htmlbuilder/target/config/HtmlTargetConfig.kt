package edu.kata.htmlbuilder.target.config

import edu.kata.htmlbuilder.target.core.usecase.WriteHtmlUseCaseImpl
import edu.kata.htmlbuilder.target.core.usecase.WriteTextFileUseCase
import edu.kata.htmlbuilder.target.driver.usecase.WriteHtmlUseCase
import edu.kata.htmlbuilder.target.output.file.usecase.WriteTextFileUseCaseImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val htmlTargetModule = module {
    single<WriteTextFileUseCase> { WriteTextFileUseCaseImpl() }
    singleOf(::WriteHtmlUseCaseImpl) {
        bind<WriteHtmlUseCase>()
    }
}