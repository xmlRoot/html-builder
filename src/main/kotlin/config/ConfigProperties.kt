package com.htmlbuilder.config

import java.util.*

private const val CONFIG = "config.properties"

object ConfigProperties {
    private val properties = Properties()

    init {
        val file = this::class.java.classLoader.getResourceAsStream(CONFIG)
        properties.load(file)
    }

    fun getProperty(key: String): String = properties.getProperty(key)
}