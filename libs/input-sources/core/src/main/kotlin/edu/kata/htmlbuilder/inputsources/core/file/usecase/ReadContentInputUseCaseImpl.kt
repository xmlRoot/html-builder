package edu.kata.htmlbuilder.inputsources.core.file.usecase

import edu.kata.htmlbuilder.common.logging.LoggerDelegate
import edu.kata.htmlbuilder.inputsource.driver.model.FileContentInput
import edu.kata.htmlbuilder.inputsource.driver.model.ContentInput
import edu.kata.htmlbuilder.inputsource.driver.model.ContentInputValue
import edu.kata.htmlbuilder.inputsource.driver.model.StringContentInput
import edu.kata.htmlbuilder.inputsource.driver.model.StringInputValue
import edu.kata.htmlbuilder.inputsource.driver.usecase.ReadContentInputUseCase
import java.io.FileNotFoundException
import java.nio.file.Path
import kotlin.io.path.absolute
import kotlin.io.path.exists

class ReadContentInputUseCaseImpl : ReadContentInputUseCase {

    private val log by LoggerDelegate()

    override fun invoke(input: ContentInput): ContentInputValue =
        when(input) {
            is FileContentInput -> readFile(input)
            is StringContentInput -> StringInputValue(input.content)
        }

    private fun readFile(input : FileContentInput) : ContentInputValue{
        val fileLocation = Path.of(input.path)
        if (!fileLocation.exists()){
            throw FileNotFoundException("No file found at ${fileLocation.toAbsolutePath()}")
        }
        return fileLocation.toFile().readText().let {
            log.debug("Read file {} ", fileLocation.absolute())
            StringInputValue(it)
        }
    }
}