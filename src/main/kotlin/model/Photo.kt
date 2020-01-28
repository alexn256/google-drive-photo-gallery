package model

import java.time.LocalDateTime

/**
 * Represents info and metadata (exif data) of photo.
 *
 * @author Alexander Naumov.
 */
class Photo {
    var name: String = ""
    var type: String = ""
    var height: Int = 0
    var width: Int = 0
    var date: LocalDateTime? = null
    var latitude: String = ""
    var longitude: String = ""
    var device: String = ""
}