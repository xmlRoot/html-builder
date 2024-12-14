package edu.kata.htmlbuilder.app.config

import edu.kata.htmlbuilder.app.core.usecase.ConfigureApplicationUseCaseImpl
import edu.kata.htmlbuilder.app.driver.usecase.ConfigureApplicationUseCase
import edu.kata.htmlbuilder.builder.adapter.HtmlBuilderAdapter
import edu.kata.htmlbuilder.inputsources.adapter.InputSourcesAdapter
import edu.kata.htmlbuilder.inputsources.adapter.InputSourcesAdapterImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import edu.kata.htmlbuilder.builder.adapter.HtmlBuilderAdapterImpl

val appModule = module {
    single<ConfigureApplicationUseCase> { ConfigureApplicationUseCaseImpl() }
    singleOf(::InputSourcesAdapterImpl) {
        bind<InputSourcesAdapter>()
    }
    singleOf(::HtmlBuilderAdapterImpl) {
        bind<HtmlBuilderAdapter>()
    }
}
