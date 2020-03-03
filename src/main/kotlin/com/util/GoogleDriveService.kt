package com.util

import com.model.Album
import com.model.Photo
import java.io.File

/**
 *
 * @author Alexander Naumov.
 */
object GoogleDriveService {

    /**
     * Get all [Album]s.
     */
    fun getAlbums(): List<Album> {
        val albumFiles = GoogleDriveUtils.getFiles()
        return albumFiles.map { folder ->
            val photoFiles = GoogleDriveUtils.getFiles(folder.id)
            val album = Album()
            album.id = folder.id
            album.name = folder.name
            album.size = photoFiles.size
            photoFiles.map {
                val ins = GoogleDriveUtils.getFile(it.id)
                val photo = ExifExtractor.getExif(ins)
                photo.name = it.name
                photo.id = it.id
                photo
            }.asSequence().forEach { album.photos.add(it) }
            album
        }
    }

    /**
     * Get [Photo] by name.
     */
    fun getPhoto(name: String): Photo {
        return Photo()
    }

    /**
     * Get [Album] by name.
     */
    fun getAlbum(name: String): Album {
        return Album()
    }

    /**
     * Add photo to [albumName] directory
     * on Google Drive disk.
     */
    fun addPhoto(file: File, albumName: String) {

    }

    /**
     * Remove photo by [name] on Google Drive disk.
     */
    fun deletePhoto(name: String): Boolean {
        return false
    }
}