package edu.kata.htmlbuilder.builder.core.model

import edu.kata.htmlbuilder.builder.core.tag.model.InlineTag
import edu.kata.htmlbuilder.builder.core.tag.model.Tag
import edu.kata.htmlbuilder.builder.core.tag.serializer.TagSerializer
import edu.kata.htmlbuilder.builder.core.tag.model.TagWithContent
import edu.kata.htmlbuilder.builder.core.tag.model.TagWithInnerHtml
import edu.kata.htmlbuilder.builder.core.tag.serializer.BodySerializer
import edu.kata.htmlbuilder.builder.core.tag.serializer.DivSerializer
import edu.kata.htmlbuilder.builder.core.tag.serializer.H1Serializer
import edu.kata.htmlbuilder.builder.core.tag.serializer.HeadSerializer
import edu.kata.htmlbuilder.builder.core.tag.serializer.HtmlSerializer
import edu.kata.htmlbuilder.builder.core.tag.serializer.LinkSerializer
import edu.kata.htmlbuilder.builder.core.tag.serializer.ParagraphSerializer
import edu.kata.htmlbuilder.builder.core.tag.serializer.SpanSerializer
import edu.kata.htmlbuilder.builder.core.tag.serializer.TitleSerializer
import edu.kata.htmlbuilder.builder.driver.model.HtmlBodyDsl
import edu.kata.htmlbuilder.builder.driver.model.HtmlBodyTagListDSl
import edu.kata.htmlbuilder.builder.driver.model.HtmlTagDSL
import edu.kata.htmlbuilder.builder.driver.model.HtmlTagPropertiesDsl
import edu.kata.htmlbuilder.builder.driver.model.TagContainer
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


abstract class TagContainerDSLImpl : TagContainer {

    protected abstract fun addTag(tag : Tag)

    override fun tag(name: TagName, tagBuilder: HtmlTagDSL.() -> Unit) {
        HtmlTagDSLImpl().apply { tagBuilder() }.let {
            if (it.innerHtml.isEmpty()) {
                if (it.content.isNotBlank()) {
                    addTag(
                        TagWithContent(
                            content = it.content,
                            properties = it.props,
                            serializer = name.serializer
                        )
                    )
                } else addTag(
                    InlineTag(properties = it.props, serializer = name.serializer),
                )
            } else addTag(
                TagWithInnerHtml(
                    properties = it.props,
                    serializer = name.serializer,
                    innerHtml = it.innerHtml
                )
            )
        }
    }

    private val TagName.serializer: TagSerializer
        get() = when (this) {
            BODY -> BodySerializer
            DIV -> DivSerializer
            HEAD -> HeadSerializer
            HTML -> HtmlSerializer
            LINK -> LinkSerializer
            PARAGRAPH -> ParagraphSerializer
            SPAN -> SpanSerializer
            TITLE -> TitleSerializer
            H1 -> H1Serializer
        }
}

class HtmlBodyDslImpl : HtmlBodyDsl, TagContainerDSLImpl() {
    private val tags: MutableList<Tag> = mutableListOf()
    override fun addTag(tag: Tag) {
        tags.add(tag)
    }

    val innerTags : List<Tag>
        get() = tags
}

class HtmlTagDSLImpl : HtmlTagDSL {

    var props: Map<String, String> = emptyMap()

    override fun properties(propsBuilder: HtmlTagPropertiesDsl.() -> Unit) {
        HtmlTagPropertiesDslImpl().apply { propsBuilder() }.let {
            props = it.props
        }
    }

    override var content: String = ""

    var innerHtml: List<Tag> = emptyList()

    override fun innerHtml(innerBuilder: HtmlBodyTagListDSl.() -> Unit) {
        HtmlBodyDslImpl().apply { innerBuilder() }.let {
            innerHtml = it.innerTags
        }
    }

}

class HtmlTagPropertiesDslImpl : HtmlTagPropertiesDsl {

    val props: MutableMap<String, String> = mutableMapOf()

    override fun property(name: String, value: String) {
        props[name] = value
    }

}