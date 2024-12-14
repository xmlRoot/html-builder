package edu.kata.htmlbuilder.inputsource.driver.model

sealed interface ContentInput

data class FileContentInput(
    val path: String
) : ContentInput

data class StringContentInput(
    val content: String
) : ContentInput