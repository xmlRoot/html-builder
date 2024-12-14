package edu.kata.htmlbuilder.app.ui.cli.command

import edu.kata.htmlbuilder.app.driver.model.ApplicationConfiguration
import edu.kata.htmlbuilder.app.driver.usecase.ConfigureApplicationUseCase
import edu.kata.htmlbuilder.common.logging.LoggerDelegate
import edu.kata.htmlbuilder.builder.adapter.HtmlBuilderAdapter
import edu.kata.htmlbuilder.builder.model.dto.FileHtmlBuilderContentInput
import picocli.CommandLine.Command
import picocli.CommandLine.Option
import picocli.CommandLine.Parameters

@Command(
    name = "html-builder", mixinStandardHelpOptions = true, version = ["htmlbuilder 1.0"],
    description = ["Generates an html file based on provided text files."]
)
class CliCommands(
    private val initApp: ConfigureApplicationUseCase,
    private val builder : HtmlBuilderAdapter,
) : Runnable {

    private val log by LoggerDelegate()

    @Option(
        names = ["-s", "--show-on-screen"],
        description = ["Show the html content as cli output."]
    )
    var showOnScreen = false

    @Option(
        names = ["-o", "--output-file"],
        description = ["Output file name."]
    )
    var outputFilename = "output.html"

    @Option(
        names = ["-d", "--output-file-dir"],
        description = ["Output file directory."]
    )
    var outputFileDir = "."

    @Option(
        names = ["-v", "--verbose"],
        description = ["Make the output more verbose. Useful for debugging or when you are a nerd."]
    )
    var verbose = false

    @Parameters(description = ["Target html page list of text content files"])
    var files: Array<String> = emptyArray()

    override fun run() {
        try {
            doRun()
        } catch (ex: Throwable) {
            if (verbose) {
                log.error("Execution failed due to: ", ex)
            } else log.error(ex.message)
        }
    }

    private fun doRun() {
        initApp(ApplicationConfiguration(verbose = verbose))
        builder.buildToFile(htmlContent = FileHtmlBuilderContentInput(
            files = files.toList(),
            outputFileName = outputFilename,
            destinationDir = outputFileDir
        )
        ).also { output ->
            log.info("File is located at {}", output.outputFile)
        }
    }

}