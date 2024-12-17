package com.htmlbuilder.input.file

import com.htmlbuilder.input.InputDataSource
import org.apache.logging.log4j.kotlin.logger
import java.io.File

private const val INPUT_DIRECTORY = "./input/"

class FileInputDataSource : InputDataSource {

    private val logger = logger()

    override fun getInput(arg: String): String {
        logger.info("Reading from $INPUT_DIRECTORY folder")
        File(INPUT_DIRECTORY).let { input ->
            if (input.exists().not()) {
                input.mkdirs()
                throw IllegalArgumentException("Use '$INPUT_DIRECTORY' folder for the input file sources")
            }
        }
        return File(INPUT_DIRECTORY + arg).readText()
    }
}