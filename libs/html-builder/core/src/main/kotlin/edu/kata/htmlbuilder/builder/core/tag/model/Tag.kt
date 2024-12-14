package edu.kata.htmlbuilder.builder.core.tag.model

import edu.kata.htmlbuilder.builder.core.tag.chain.model.HtmlOutput
import edu.kata.htmlbuilder.builder.core.tag.serializer.TagSerializer

sealed interface Tag {
    val id : String
    val properties : Map<String, String>
    val serializer: TagSerializer
    fun render(output : HtmlOutput)
    fun markAsLastInChain()
}