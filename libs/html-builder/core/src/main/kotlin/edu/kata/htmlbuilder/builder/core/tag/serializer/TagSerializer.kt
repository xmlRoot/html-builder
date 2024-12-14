package edu.kata.htmlbuilder.builder.core.tag.serializer

import edu.kata.htmlbuilder.builder.driver.model.TagName

interface TagSerializer {
    val token : TagName
    fun open(properties : Map<String, String> = emptyMap()) : String
    fun close() : String
    fun inline(properties : Map<String, String> = emptyMap()) : String
}