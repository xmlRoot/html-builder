package com.htmlbuilder.converter.file

import com.htmlbuilder.converter.ContentTypeConverter
import com.htmlbuilder.model.dto.ContentType

class FileContentTypeConverter : ContentTypeConverter {

    override fun toType(content: String): ContentType {
        return when (content.substringAfterLast(".").trim()) {
            "md" -> ContentType.BLOCKQUOTE
            "code" -> ContentType.CODE
            else -> ContentType.PARAGRAPH
        }
    }
}