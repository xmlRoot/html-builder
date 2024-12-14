package edu.kata.htmlbuilder.inputsource.driver.usecase

import edu.kata.htmlbuilder.inputsource.driver.model.ContentInput
import edu.kata.htmlbuilder.inputsource.driver.model.ContentInputValue

interface ReadContentInputUseCase {
    operator fun invoke(input : ContentInput) : ContentInputValue
}