package util

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.services.drive.Drive
import model.Album
import model.Photo
import java.io.File

/**
 *
 * @author Alexander Naumov.
 */
class GoogleDriveService {

    private val drive:Drive

    constructor() {
        val appName = PropertyLoader.getProp("applicationName")
        val jsonFactory = JacksonFactory.getDefaultInstance()
        //build a new authorized API client service.
        val httpTransport = GoogleNetHttpTransport.newTrustedTransport()
        val credentials = CredentialsLoader.load(httpTransport, jsonFactory)
        drive = Drive.Builder(httpTransport, jsonFactory, credentials)
                .setApplicationName(appName).build()
    }

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