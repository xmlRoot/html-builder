package edu.kata.htmlbuilder.app.core.usecase

import edu.kata.htmlbuilder.app.driver.model.ApplicationConfiguration
import edu.kata.htmlbuilder.app.driver.usecase.ConfigureApplicationUseCase
import org.slf4j.LoggerFactory
import ch.qos.logback.classic.Level
import ch.qos.logback.classic.Logger
import ch.qos.logback.classic.LoggerContext

internal class ConfigureApplicationUseCaseImpl : ConfigureApplicationUseCase {

    override fun invoke(config: ApplicationConfiguration) {
        val context = LoggerFactory.getILoggerFactory() as LoggerContext
        context.getLogger(Logger.ROOT_LOGGER_NAME).apply {
            level = if (config.verbose) Level.DEBUG else Level.INFO
        }
    }
}