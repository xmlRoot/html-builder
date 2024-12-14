package edu.kata.htmlbuilder.target.output.file.usecase

import edu.kata.htmlbuilder.target.core.usecase.WriteTextFileUseCase
import java.nio.file.Path

class WriteTextFileUseCaseImpl : WriteTextFileUseCase {

    override fun invoke(content: String, target: Path): Path =
        with(target.toFile()) {
            if (!exists()) {
                createNewFile()
            }
            writeText(content)
            Path.of(canonicalPath)
        }


}