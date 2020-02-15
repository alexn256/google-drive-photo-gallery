package com.util

import com.drew.imaging.ImageMetadataReader
import com.model.Photo
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 *  Exif metadata extractor.
 *
 * @author Alexander Naumov.
 */
class ExifExtractor {

    private val dateFormatter: DateTimeFormatter =  DateTimeFormatter.ofPattern("yyyy:MM:dd HH:mm:ss")

    /**
     * Convert [File] to [Photo] entity.
     */
    fun getExif(photo: File): Photo {
        val metadata = ImageMetadataReader.readMetadata(photo)
        val exif = Photo()
        metadata.directories.forEach { dir ->
            dir.tags.forEach { tag ->
                when (dir.name) {
                    "JPEG" -> when (tag.tagName) {
                        "Image Height" -> exif.height = tag.description.split(" ")[0].toInt()
                        "Image Width" -> exif.width = tag.description.split(" ")[0].toInt()
                    }
                    "Exif IFD0" -> when (tag.tagName) {
                        "Date/Time" -> exif.date = LocalDateTime
                            .parse(tag.description, dateFormatter)
                        "Model" ->  exif.device = tag.description
                    }
                    "GPS" -> when (tag.tagName) {
                        "GPS Latitude" -> exif.latitude = tag.description.substring(0, tag.description.length - 1)
                        "GPS Longitude" -> exif.longitude = tag.description.substring(0, tag.description.length - 1)
                    }
                    "File" -> when(tag.tagName) {
                        "File Name" -> {
                            val fullName = tag.description.split(".")
                            exif.name = fullName[0]
                            exif.type = fullName[1]
                        }
                    }
                }
            }
        }
        return exif
    }
}