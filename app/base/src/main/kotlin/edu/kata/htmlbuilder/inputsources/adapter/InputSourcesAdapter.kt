package edu.kata.htmlbuilder.inputsources.adapter

import edu.kata.htmlbuilder.inputsources.adapter.model.dto.FileContent

interface InputSourcesAdapter {

    fun readFile(file: String): FileContent

}