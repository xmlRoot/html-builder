package edu.kata.htmlbuilder.inputsource.driver.model

sealed interface ContentInputValue{
    val value : String
}

data class StringInputValue(
    override val value: String
) : ContentInputValue {
    override fun toString(): String = value
}