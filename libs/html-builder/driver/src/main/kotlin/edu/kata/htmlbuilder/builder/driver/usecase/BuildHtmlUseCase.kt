package edu.kata.htmlbuilder.builder.driver.usecase

import edu.kata.htmlbuilder.builder.driver.model.HtmlBuilderDsl
import edu.kata.htmlbuilder.builder.driver.model.HtmlText

interface BuildHtmlUseCase {
    operator fun invoke(
        htmlProps : Map<String, String> = emptyMap(),
        htmlBuilder : HtmlBuilderDsl.() -> Unit
    ) : HtmlText
}