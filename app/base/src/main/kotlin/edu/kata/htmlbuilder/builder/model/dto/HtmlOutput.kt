package edu.kata.htmlbuilder.builder.model.dto

import java.nio.file.Path

sealed interface HtmlOutput {
    val html : String
}

data class FileHtmlOutput(
    override val html : String,
    val outputFile : Path
) : HtmlOutput
