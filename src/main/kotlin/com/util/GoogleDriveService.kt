package com.util

import com.model.Album
import com.model.Photo
import java.io.File

/**
 *
 * @author Alexander Naumov.
 */
object GoogleDriveService {

    private val albums: Map<String, Album>

    init {
        albums = getAlbums()
    }

    /**
     * Get all [Album]s.
     */
    fun getAlbums(): Map<String, Album> {
        if (albums.isEmpty()) {
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
                    photo.webContentLink = it.webContentLink
                    photo
                }.asSequence().forEach { album.photos.add(it) }
                album
            }.map { it.name to it }.toMap()
        } else {
            return albums
        }
    }

    /**
     * Get [Photo] by name.
     */
    fun getPhoto(name: String): Photo? {
        if (albums.isNotEmpty()) {
            albums.forEach { return it.value.photos.first { photo -> photo.name == name } }
        }
        return null
    }

    /**
     * Get [Album] by name.
     */
    fun getAlbum(name: String): Album? {
        return albums[name]
    }

    /**
     * Add photo to [albumName] directory
     * on Google Drive disk.
     */
    fun addPhoto(file: File, albumName: String): Boolean {
        //todo: not implemented.
        return false
    }

    /**
     * Remove photo by [name] on Google Drive disk.
     */
    fun deletePhoto(name: String): Boolean {
        //todo: not implemented.
        return false
    }
}