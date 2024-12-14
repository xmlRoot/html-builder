package edu.kata.htmlbuilder.target.driver.model

import java.nio.file.Path

data class HtmlContent(
    val html : String,
    val target : HtmlContentTarget
)

sealed interface HtmlContentTarget

data class FileHtmlContentTarget(
    val outputFile: Path,
) : HtmlContentTarget
