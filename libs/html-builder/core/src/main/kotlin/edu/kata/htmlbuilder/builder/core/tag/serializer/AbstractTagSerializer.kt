package edu.kata.htmlbuilder.builder.core.tag.serializer

import edu.kata.htmlbuilder.builder.driver.model.TagName
import edu.kata.htmlbuilder.builder.driver.model.TagName.BODY
import edu.kata.htmlbuilder.builder.driver.model.TagName.DIV
import edu.kata.htmlbuilder.builder.driver.model.TagName.H1
import edu.kata.htmlbuilder.builder.driver.model.TagName.HEAD
import edu.kata.htmlbuilder.builder.driver.model.TagName.HTML
import edu.kata.htmlbuilder.builder.driver.model.TagName.LINK
import edu.kata.htmlbuilder.builder.driver.model.TagName.PARAGRAPH
import edu.kata.htmlbuilder.builder.driver.model.TagName.SPAN
import edu.kata.htmlbuilder.builder.driver.model.TagName.TITLE

sealed class AbstractTagSerializer(
    override val token : TagName
) : TagSerializer {

    override fun open(properties: Map<String, String>): String = "<${token.value}${properties.toPropsString()}>"
    override fun close(): String = "</${token.value}>"

    override fun inline(properties: Map<String, String>): String {
        return "</${token.value}${properties.toPropsString()}>"
    }

    private fun Map<String, String>.toPropsString(): String =
        if (isEmpty()) ""
        else map { (key, value) -> " $key=\"$value\"" }.joinToString(separator = " ")
}

data object LinkSerializer : AbstractTagSerializer(LINK) {
    override fun inline(properties: Map<String, String>): String = open(properties)
}
data object DivSerializer : AbstractTagSerializer(DIV)
data object ParagraphSerializer : AbstractTagSerializer(PARAGRAPH)
data object HeadSerializer : AbstractTagSerializer(HEAD)
data object TitleSerializer : AbstractTagSerializer(TITLE)
data object BodySerializer : AbstractTagSerializer(BODY)
data object HtmlSerializer : AbstractTagSerializer(HTML)
data object SpanSerializer : AbstractTagSerializer(SPAN)
data object H1Serializer : AbstractTagSerializer(H1)

