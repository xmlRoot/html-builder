package edu.kata.htmlbuilder.builder.core.tag.model

import edu.kata.htmlbuilder.builder.core.tag.chain.model.HtmlOutput
import edu.kata.htmlbuilder.builder.core.tag.serializer.TagSerializer

class InlineTag(
    override val id: String = tagId(),
    override val serializer: TagSerializer,
    override val properties: Map<String, String> = emptyMap(),
) : Tag {

    private var last : Boolean = false

    override fun render(output: HtmlOutput) {
        output.inline(this)
    }

    override fun markAsLastInChain() {
        last = true
    }

    override fun toString(): String =
        "Tag<${serializer.token}>"
}