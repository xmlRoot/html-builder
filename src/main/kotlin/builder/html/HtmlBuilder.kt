package com.htmlbuilder.builder.html

import com.htmlbuilder.builder.Builder
import com.htmlbuilder.model.dto.ContentBlock
import com.htmlbuilder.model.dto.ContentType

private const val CONTENT_MARK = "{\$content}"

class HtmlBuilder : Builder {

    override fun build(contentBlocks: List<ContentBlock>): String {
        val contentToInsert =
            contentBlocks.joinToString("\n", "\n", "\n")
            { getOpenTag(it.type) + it.content + getCloseTag(it.type) }

        return HtmlBuilder::class.java.getResource("/html/html-template.html")?.readText().orEmpty()
            .replace(CONTENT_MARK, contentToInsert)
    }

    private fun getOpenTag(contentType: ContentType) = when (contentType) {
        ContentType.PARAGRAPH -> "<p>"
        ContentType.BLOCKQUOTE -> "<blockquote>"
        ContentType.CODE -> "<pre><code>"
    }

    private fun getCloseTag(contentType: ContentType) = when (contentType) {
        ContentType.PARAGRAPH -> "</p>"
        ContentType.BLOCKQUOTE -> "</blockquote>"
        ContentType.CODE -> "</code></pre>"
    }
}