package edu.kata.htmlbuilder.builder.core.tag.model

import edu.kata.htmlbuilder.builder.core.tag.chain.model.HtmlOutput
import edu.kata.htmlbuilder.builder.core.tag.serializer.TagSerializer

class TagWithContent(
    override val id: String = tagId(),
    override val serializer: TagSerializer,
    val content : String,
    override val properties: Map<String, String> = emptyMap(),
) : Tag {

    private var last : Boolean = false

    override fun render(output: HtmlOutput) {
        output.open(this)
        output.content(this)
        output.close(this)
    }

    override fun markAsLastInChain() {
        last = true
    }

    override fun toString(): String =
        "Tag<${serializer.token}>"

}