package edu.kata.htmlbuilder.target.driver.usecase

import edu.kata.htmlbuilder.target.driver.model.HtmlContent
import edu.kata.htmlbuilder.target.driver.model.HtmlContentOutput

interface WriteHtmlUseCase {
    operator fun invoke(content: HtmlContent) : HtmlContentOutput
}