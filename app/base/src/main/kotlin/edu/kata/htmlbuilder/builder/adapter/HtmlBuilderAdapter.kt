package edu.kata.htmlbuilder.builder.adapter

import edu.kata.htmlbuilder.builder.model.dto.FileHtmlBuilderContentInput
import edu.kata.htmlbuilder.builder.model.dto.FileHtmlOutput

interface HtmlBuilderAdapter {

    fun buildToFile(htmlContent : FileHtmlBuilderContentInput): FileHtmlOutput

}