package edu.kata.htmlbuilder.builder.adapter

import edu.kata.htmlbuilder.builder.driver.model.HtmlText
import edu.kata.htmlbuilder.builder.driver.usecase.BuildHtmlUseCase
import edu.kata.htmlbuilder.builder.model.dto.FileHtmlBuilderContentInput
import edu.kata.htmlbuilder.builder.model.dto.FileHtmlOutput
import edu.kata.htmlbuilder.inputsources.adapter.InputSourcesAdapter
import edu.kata.htmlbuilder.inputsources.adapter.model.dto.FileContent
import edu.kata.htmlbuilder.target.driver.model.FileHtmlContentOutput
import edu.kata.htmlbuilder.target.driver.model.FileHtmlContentTarget
import edu.kata.htmlbuilder.target.driver.model.HtmlContent
import edu.kata.htmlbuilder.target.driver.usecase.WriteHtmlUseCase
import java.nio.file.Path

internal class HtmlBuilderAdapterImpl(
    private val inputSources: InputSourcesAdapter,
    private val writeHtml: WriteHtmlUseCase,
    private val html: BuildHtmlUseCase
) : HtmlBuilderAdapter {

    override fun buildToFile(htmlContent: FileHtmlBuilderContentInput): FileHtmlOutput =
        htmlContent.files.map { contentFile -> inputSources.readFile(contentFile) }.let { contentData ->
            val html = generateHtml(htmlContent.styled, textContent = contentData)
            writeHtml(
                HtmlContent(
                    html = html.text,
                    target = FileHtmlContentTarget(
                        outputFile =
                            Path.of(htmlContent.destinationDir, htmlContent.outputFileName)
                    )
                )
            ).let { output ->
                when (output) {
                    is FileHtmlContentOutput -> FileHtmlOutput(
                        outputFile = output.file,
                        html = html.text
                    )
                }
            }
        }


    /**
    *
    * <!DOCTYPE html>
        <html>
            <head>
                <title>Generated HTML</title>
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
                      integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
                      crossorigin="anonymous"
                >
            </head>
            <body>
               <div class="row mb-3">
                <p class="col-3 text-wrap">Content from input1.txt</p>
               <div>
                <div class="row mb-3">
                    <p class="col-3 text-wrap">Content from input2.txt</p>
                <div>
                <div class="row mb-3">
                    <p class="col-3 text-wrap">Content from input3.txt</p>
                <div>
            </body>
        </html>
    *
    *
    *
    * */
    private fun generateHtml(styled : Boolean, textContent: List<FileContent>): HtmlText =
        html(htmlProps = mapOf("lang" to "en")) {
            head {
                title("Generated HTML")
                if (styled) {
                    link(href = "https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css", props = mapOf(
                        "rel" to "stylesheet",
                        "integrity" to "sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH",
                        "crossorigin" to "anonymous"
                    ))
                }
            }
            body {
                    div {
                        properties {
                            className("container mt-5")
                        }
                        innerHtml {
                            h1 { content = "Start Wars : The Old Republic" }
                            textContent.forEachIndexed { index, file ->
                                p {
                                    properties {
                                        val classNames = StringBuilder("border border-dark p-3 mb-4 fst-italic")
                                        if (index == 0) {
                                            classNames.append(" lead text-center")
                                        }
                                        className(classNames.toString())
                                    }
                                    content = file.text
                                }
                            }
                        }
                    }
            }
        }

}