package edu.kata.htmlbuilder.builder.core.usecase

import edu.kata.htmlbuilder.builder.core.model.HtmlBuilderDslImpl
import edu.kata.htmlbuilder.builder.core.tag.chain.model.HtmlOutput
import edu.kata.htmlbuilder.builder.core.tag.model.html
import edu.kata.htmlbuilder.builder.driver.model.HtmlBuilderDsl
import edu.kata.htmlbuilder.builder.driver.model.HtmlText
import edu.kata.htmlbuilder.builder.driver.usecase.BuildHtmlUseCase

class BuildHtmlUseCaseImpl : BuildHtmlUseCase {

    private fun String.toHtmlText() = HtmlText(this)

    override fun invoke(
        htmlProps: Map<String, String>,
        htmlBuilder: HtmlBuilderDsl.() -> Unit
    ): HtmlText =
        HtmlBuilderDslImpl().apply { htmlBuilder() }.let { builder ->
            with(HtmlOutput(StringBuilder("<!DOCTYPE html>"), blankSymbol = " ")){
                html(innerHtml = builder.tags, properties = htmlProps).render(this)
                html.toHtmlText()
            }
        }
}