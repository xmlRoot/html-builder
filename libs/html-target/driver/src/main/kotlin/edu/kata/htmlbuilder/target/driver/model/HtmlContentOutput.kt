package edu.kata.htmlbuilder.target.driver.model

import java.nio.file.Path

sealed interface HtmlContentOutput

data class FileHtmlContentOutput(val file : Path) : HtmlContentOutput
