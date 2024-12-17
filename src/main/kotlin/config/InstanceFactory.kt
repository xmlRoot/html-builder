package com.htmlbuilder.config

import com.htmlbuilder.converter.ContentTypeConverter
import com.htmlbuilder.converter.file.FileContentTypeConverter
import com.htmlbuilder.converter.network.NetworkContentTypeConverter
import com.htmlbuilder.input.InputDataSource
import com.htmlbuilder.input.file.FileInputDataSource
import com.htmlbuilder.input.network.NetworkInputDataSource
import com.htmlbuilder.output.OutputDataSource
import com.htmlbuilder.output.console.ConsoleOutputDataSource
import com.htmlbuilder.output.file.FileOutputDataSource
import com.htmlbuilder.output.network.NetworkOutputDataSource

object InstanceFactory {

    fun getInputDataSource(): InputDataSource {
        return when (ConfigProperties.getProperty("input.data.source")) {
            "file" -> FileInputDataSource()
            "network" -> NetworkInputDataSource()
            else -> throw IllegalArgumentException()
        }
    }

    fun getOutputDataSource(): OutputDataSource {
        return when (ConfigProperties.getProperty("output.data.source")) {
            "file" -> FileOutputDataSource()
            "network" -> NetworkOutputDataSource()
            "console" -> ConsoleOutputDataSource()
            else -> throw IllegalArgumentException()
        }
    }

    fun getContentTypeConverter(): ContentTypeConverter {
        return when (ConfigProperties.getProperty("content.type.converter")) {
            "file" -> FileContentTypeConverter()
            "network" -> NetworkContentTypeConverter()
            else -> throw IllegalArgumentException()
        }
    }
}