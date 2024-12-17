package com.htmlbuilder.output.console

import com.htmlbuilder.output.OutputDataSource

class ConsoleOutputDataSource : OutputDataSource {
    override fun putOutput(output: String) {
        println(output)
    }
}