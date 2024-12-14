package edu.kata.htmlbuilder.target.core.usecase

import java.nio.file.Path

interface WriteTextFileUseCase {
    operator fun invoke(content : String, target : Path) : Path
}