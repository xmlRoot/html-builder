package edu.kata.htmlbuilder.builder.core.tag.model

import edu.kata.htmlbuilder.builder.core.tag.chain.model.HtmlOutput
import edu.kata.htmlbuilder.builder.core.tag.serializer.TagSerializer

data class TagWithInnerHtml(
    override val id: String = tagId(),
    override val serializer: TagSerializer,
    val innerHtml: List<Tag>,
    override val properties: Map<String, String>,
) : Tag {

    private var last : Boolean = false
    override fun render(output: HtmlOutput) {
        output.newLine()
        output.indent()
        output.open(this)
            innerHtml.forEach { innerTag ->
                output.newLine()
                innerTag.render(output)
            }
        output.close(this)
    }

    override fun markAsLastInChain() {
        last = true
    }

    override fun toString(): String =
        "Tag<${serializer.token}>"
}