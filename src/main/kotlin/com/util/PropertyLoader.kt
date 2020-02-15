package com.util

import java.util.*

/**
 * Property extractor.
 *
 * @author Alexander Naumov.
 */
object PropertyLoader {

    private const val props = "application.properties"

    private val input = PropertyLoader::class.java.classLoader.getResourceAsStream(props)
    private val prop = Properties()

    /**
     * Get property by [name].
     */
    fun getProp(name: String): String {
        if (prop.isEmpty) {
            prop.load(input)
        }
        return prop.getProperty(name)
    }
}