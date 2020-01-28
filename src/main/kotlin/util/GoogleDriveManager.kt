package util

import model.Album
import model.Photo
import java.io.File

class GoogleDriveManager {

    /**
     * Get all [Album]s.
     */
    fun getAlbums():List<Album> {
        return listOf()
    }

    /**
     * Get [Photo] by name.
     */
    fun getPhoto(name:String): Photo {
        return Photo()
    }

    /**
     * Get [Album] by name.
     */
    fun getAlbum(name:String): Album {
        return Album()
    }

    /**
     * Add photo to [albumName] directory
     * on Google Drive disk.
     */
    fun addPhoto(file: File, albumName:String) {

    }

    /**
     * Remove photo by [name] on Google Drive disk.
     */
    fun deletePhoto(name:String):Boolean {
        return false
    }
}