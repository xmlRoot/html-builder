package edu.kata.htmlbuilder.inputsources.adapter

import edu.kata.htmlbuilder.inputsource.driver.model.FileContentInput
import edu.kata.htmlbuilder.inputsource.driver.usecase.ReadContentInputUseCase
import edu.kata.htmlbuilder.inputsources.adapter.model.dto.FileContent

internal class InputSourcesAdapterImpl(
    private val doRead : ReadContentInputUseCase
) : InputSourcesAdapter {

    override fun readFile(file: String): FileContent =
        FileContent(
            location = file,
            text = doRead(FileContentInput(file)).value
        )
}