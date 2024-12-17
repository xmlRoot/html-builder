package com.htmlbuilder.converter

import com.htmlbuilder.model.dto.ContentType

interface ContentTypeConverter {

    fun toType(content: String): ContentType
}