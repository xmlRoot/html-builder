package edu.kata.htmlbuilder.target.core.usecase

import edu.kata.htmlbuilder.target.driver.model.FileHtmlContentOutput
import edu.kata.htmlbuilder.target.driver.model.FileHtmlContentTarget
import edu.kata.htmlbuilder.target.driver.model.HtmlContent
import edu.kata.htmlbuilder.target.driver.model.HtmlContentOutput
import edu.kata.htmlbuilder.target.driver.usecase.WriteHtmlUseCase
import java.nio.file.Path

class WriteHtmlUseCaseImpl(
    private val writeTextFile : WriteTextFileUseCase,
) : WriteHtmlUseCase {

    override fun invoke(content: HtmlContent): HtmlContentOutput =
        when(content.target) {
            is FileHtmlContentTarget ->
                writeToFile(content.html, (content.target as FileHtmlContentTarget).outputFile)
        }

    private fun writeToFile(html : String, outputFile: Path) =
        writeTextFile(html, target = outputFile).let { path ->
            FileHtmlContentOutput(path)
        }

}