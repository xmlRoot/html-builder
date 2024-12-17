package com.htmlbuilder.app

import com.htmlbuilder.builder.html.HtmlBuilder
import com.htmlbuilder.config.InstanceFactory
import com.htmlbuilder.model.dto.ContentBlock

val inputDataSource = InstanceFactory.getInputDataSource()
val outputDataSource = InstanceFactory.getOutputDataSource()
val contentTypeConverter = InstanceFactory.getContentTypeConverter()

class BuilderApplication {

    fun process(args: Array<String>) {

        val contentBlocks = args.map {
            val type = contentTypeConverter.toType(it)
            val content = inputDataSource.getInput(it)
            ContentBlock(type, content)
        }.toList()

        val output = HtmlBuilder().build(contentBlocks)

        outputDataSource.putOutput(output)
    }
}