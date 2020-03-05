package com.util

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.services.drive.Drive
import java.io.*
import com.google.api.services.drive.model.File as GoogleFile


/**
 * Class for access to Google drive disk.
 *
 * @author Alexander Naumov.
 */
object GoogleDriveUtils {

    private val appName = PropertyLoader.getProp("applicationName")
    private val jsonFactory = JacksonFactory.getDefaultInstance()
    private val httpTransport = GoogleNetHttpTransport.newTrustedTransport()
    private val credentials = CredentialsLoader.load(httpTransport, jsonFactory)

    private val os = ByteArrayOutputStream()
    private val drive = Drive.Builder(httpTransport, jsonFactory, credentials).setApplicationName(appName).build()

    /**
     * Retrieves all files relative to current directory.
     *
     * @param id parent catalog id.
     */
    @Throws(IOException::class)
    fun getFiles(id: String? = null): List<GoogleFile> {
        var pageToken: String? = null
        val list = ArrayList<com.google.api.services.drive.model.File>()
        val query = if (id == null) {
            " mimeType = 'application/vnd.google-apps.folder' and 'root' in parents"
        } else {
            "'$id' in parents"
        }
        do {
           val result = drive.files().list().setQ(query).setSpaces("drive")
                    .setFields("nextPageToken, files(id, name, webContentLink)").setPageToken(pageToken).execute()
            result.files.forEach { list.add(it) }
            pageToken = result.nextPageToken
        } while (pageToken != null)
        return list
    }

    /**
     * Download file from directory (album) as [ByteArrayInputStream].
     *
     * @param id album id.
     */
    fun getFile(id: String): ByteArrayInputStream {
        val os = ByteArrayOutputStream()
        drive.files().get(id).executeMediaAndDownloadTo(os)
        return ByteArrayInputStream(os.toByteArray())
    }
}