package com.util

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.services.drive.Drive
import com.model.Album
import com.model.Photo
import java.io.File
import java.io.IOException

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
        getGoogleSubFolders(null).forEach {folder ->
            println("Folder ID: ${folder.id} Name: ${folder.name}")
        }
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

    /**
     *
     */
    @Throws(IOException::class)
    private fun getGoogleSubFolders(parentId:String?):List<com.google.api.services.drive.model.File> {
        var pageToken:String? = null
        val list = ArrayList<com.google.api.services.drive.model.File>()
        val query = if (parentId == null) {
            " mimeType = 'application/vnd.google-apps.folder' and 'root' in parents"
        } else {
            " mimeType = 'application/vnd.google-apps.folder' and '$parentId' in parents"
        }
        do {
            val result = drive.files().list().setQ(query).setSpaces("drive")
                    .setFields("nextPageToken, files(id, name, createdTime)").setPageToken(pageToken).execute()
            result.files.forEach { list.add(it) }
            pageToken = result.nextPageToken
        } while (parentId != null)
        return list
    }
}