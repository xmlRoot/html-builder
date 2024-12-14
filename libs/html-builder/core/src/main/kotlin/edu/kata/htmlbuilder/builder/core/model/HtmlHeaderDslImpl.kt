package edu.kata.htmlbuilder.builder.core.model

import edu.kata.htmlbuilder.builder.core.tag.model.InlineTag
import edu.kata.htmlbuilder.builder.core.tag.model.Tag
import edu.kata.htmlbuilder.builder.core.tag.model.TagWithContent
import edu.kata.htmlbuilder.builder.core.tag.serializer.LinkSerializer
import edu.kata.htmlbuilder.builder.core.tag.serializer.TitleSerializer
import edu.kata.htmlbuilder.builder.driver.model.HtmlHeaderDsl

class HtmlHeaderDslImpl : HtmlHeaderDsl {

    private val headerTags : MutableList<Tag> = mutableListOf()
    val tags : List<Tag>
        get() = headerTags

    override fun title(title: String) {
        headerTags.add(
            TagWithContent(
                content = title,
                serializer = TitleSerializer
            )
        )
    }

    override fun link(href: String, props : Map<String, String>) {
        headerTags.add(InlineTag(serializer = LinkSerializer, properties = mapOf(
            "href" to href,
        ) + props
        ))
    }


}