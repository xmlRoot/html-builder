package com.htmlbuilder.output.file

import com.htmlbuilder.output.OutputDataSource
import org.apache.logging.log4j.kotlin.logger
import java.io.File

private const val OUTPUT_DIRECTORY = "./output/"
private const val OUTPUT_FILENAME = "output.html"

class FileOutputDataSource : OutputDataSource {

    private val logger = logger()

    override fun putOutput(output: String) {
        logger.info("Writing into $OUTPUT_DIRECTORY$OUTPUT_FILENAME")
        File(OUTPUT_DIRECTORY).let { out ->
            if (out.exists().not()) {
                out.mkdirs()
                logger.warn("New output folder was created: $OUTPUT_DIRECTORY")
            }
        }
        File(OUTPUT_DIRECTORY + OUTPUT_FILENAME).writeText(output)
    }
}