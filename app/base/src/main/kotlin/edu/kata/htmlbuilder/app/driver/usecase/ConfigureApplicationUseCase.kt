package edu.kata.htmlbuilder.app.driver.usecase

import edu.kata.htmlbuilder.app.driver.model.ApplicationConfiguration

interface ConfigureApplicationUseCase {
    operator fun invoke(config : ApplicationConfiguration)
}