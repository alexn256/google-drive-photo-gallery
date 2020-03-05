package com.model

import java.time.LocalDateTime

/**
 * Metadata (exif data) of photo data class.
 *
 * @author Alexander Naumov.
 */
data class Photo(
        var id: String = "",
        var name: String = "",
        var webContentLink: String = "",
        var type: String = "",
        var height: Int = 0,
        var width: Int = 0,
        var date: LocalDateTime? = null,
        var latitude: String = "",
        var longitude: String = "",
        var device: String = ""
)