package com.util

import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.JsonFactory
import com.google.api.client.util.store.FileDataStoreFactory
import com.google.api.services.drive.DriveScopes
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.InputStreamReader

/**
 * @author Alexander Naumov.
 */
object CredentialsLoader {

    private val SCOPES = listOf(DriveScopes.DRIVE)

    /**
     * Load credential from credentials file.
     *
     * @param httpTransport transport protocol.
     */
    fun load(httpTransport: NetHttpTransport, jsonFactory: JsonFactory): Credential {
        //read client secret JSON file & create Credential object.
        val filePath = PropertyLoader.getProp("credentialsLocationFile")
        val clientSecretFile = File(filePath)
        if (!clientSecretFile.exists()) {
            throw FileNotFoundException("There is no file on this path: $filePath .")
        }
        val inStream = FileInputStream(clientSecretFile)
        val clientSecrets = GoogleClientSecrets.load(jsonFactory, InputStreamReader(inStream))
        val flow = GoogleAuthorizationCodeFlow.Builder(httpTransport, jsonFactory, clientSecrets, SCOPES)
                .setDataStoreFactory(FileDataStoreFactory(clientSecretFile.parentFile))
                .setAccessType("offline").build()
        return AuthorizationCodeInstalledApp(flow, LocalServerReceiver()).authorize("user")
    }
}