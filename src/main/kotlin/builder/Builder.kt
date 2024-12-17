package com.htmlbuilder.builder

import com.htmlbuilder.model.dto.ContentBlock

interface Builder {

    fun build(contentBlocks: List<ContentBlock>): String
}