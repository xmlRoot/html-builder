package edu.kata.htmlbuilder.builder.model.dto

sealed interface HtmlBuilderContentInput

data class FileHtmlBuilderContentInput(
    val files : List<String>,
    val outputFileName : String,
    val destinationDir : String,
    val styled : Boolean = true
) : HtmlBuilderContentInput