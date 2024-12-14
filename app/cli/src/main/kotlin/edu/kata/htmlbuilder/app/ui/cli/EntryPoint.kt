package edu.kata.htmlbuilder.app.ui.cli

import edu.kata.htmlbuilder.app.config.appModule
import edu.kata.htmlbuilder.app.ui.cli.command.CliCommands
import edu.kata.htmlbuilder.builder.config.builderModule
import edu.kata.htmlbuilder.inputsources.config.inputSourcesModules
import edu.kata.htmlbuilder.target.config.htmlTargetModule
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.koin.java.KoinJavaComponent.get
import picocli.CommandLine
import kotlin.system.exitProcess

val cliModule = module {
    singleOf(::CliCommands)
}

fun main(args: Array<String>) {
    startKoin {
        modules(inputSourcesModules, appModule, cliModule, htmlTargetModule, builderModule)
    }
    val commands = get<CliCommands>(CliCommands::class.java)
    exitProcess(CommandLine(commands).execute(*args))
}