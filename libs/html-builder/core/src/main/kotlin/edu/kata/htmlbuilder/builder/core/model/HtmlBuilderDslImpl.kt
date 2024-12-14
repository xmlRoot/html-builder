package edu.kata.htmlbuilder.builder.core.model

import edu.kata.htmlbuilder.builder.core.tag.model.Tag
import edu.kata.htmlbuilder.builder.driver.model.HtmlBodyDsl
import edu.kata.htmlbuilder.builder.driver.model.HtmlBuilderDsl
import edu.kata.htmlbuilder.builder.driver.model.HtmlHeaderDsl
import edu.kata.htmlbuilder.builder.core.tag.model.head as headTag
import edu.kata.htmlbuilder.builder.core.tag.model.body as bodyTag

class HtmlBuilderDslImpl : HtmlBuilderDsl {

    val tags : MutableList<Tag> = mutableListOf()

    override fun head(headerBuilder: HtmlHeaderDsl.() -> Unit) {
        HtmlHeaderDslImpl().apply {
            headerBuilder()
        }.let {
            tags.add(headTag(innerHtml = it.tags))
        }
    }

    override fun body(bodyBuilder: HtmlBodyDsl.() -> Unit) {
        HtmlBodyDslImpl().apply { bodyBuilder() }.let {
            tags.add(bodyTag(innerHtml = it.innerTags))
        }
    }
}