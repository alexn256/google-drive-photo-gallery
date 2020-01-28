import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.client.util.store.FileDataStoreFactory
import com.google.api.services.drive.Drive
import com.google.api.services.drive.DriveScopes
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.InputStreamReader


private val JSON_FACTORY = JacksonFactory.getDefaultInstance()

private val APPLICATION_NAME = "photo galler"
private val CREDENTIALS_FOLDER = File(System.getProperty("user.home"), "credentials")
private val CLIENT_SECRET_FILE_NAME = "client_secret.json"
private val SCOPES = listOf(DriveScopes.DRIVE)


/**
 * Creates an authorized Credential object.
 * @param httpTransport The network HTTP Transport.
 * @return An authorized Credential object.
 * @throws IOException If the credentials.json file cannot be found.
*/
fun getCredentials(httpTransport: NetHttpTransport):Credential {
    val clientSecretFile = File(CREDENTIALS_FOLDER, CLIENT_SECRET_FILE_NAME)
    if (!clientSecretFile.exists()) {
        throw FileNotFoundException("Please copy $CLIENT_SECRET_FILE_NAME to folder ${CREDENTIALS_FOLDER.absolutePath}")
    }
    val inStream = FileInputStream(clientSecretFile)
    val clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, InputStreamReader(inStream))
    val flow = GoogleAuthorizationCodeFlow.Builder(httpTransport, JSON_FACTORY, clientSecrets, SCOPES)
            .setDataStoreFactory(FileDataStoreFactory(CREDENTIALS_FOLDER))
            .setAccessType("offline").build()
    return AuthorizationCodeInstalledApp(flow, LocalServerReceiver()).authorize("user")
}

fun main() {

    // 1: Create CREDENTIALS_FOLDER
   if (CREDENTIALS_FOLDER.exists()) {
       CREDENTIALS_FOLDER.mkdir()
   }
    println("Created Folder: ${CREDENTIALS_FOLDER.absolutePath}")
    println("Copy file $CLIENT_SECRET_FILE_NAME into folder above.. and rerun this class!!")

    // 2: Build a new authorized API client service.
    val httpTransport = GoogleNetHttpTransport.newTrustedTransport()

    // 3: Read client_secret.json file & create Credential object.
    val credential = getCredentials(httpTransport)

    // 4: Create Google Drive Service.
    val service = Drive.Builder(httpTransport, JSON_FACTORY, credential)
            .setApplicationName(APPLICATION_NAME).build()

    val result = service.files().list().setPageSize(10).setFields("nextPageToken, files(id, name)").execute()
    val files = result.files
    if (files == null || files.isEmpty()) {
        println("No files found.")
    } else {
        println("Files:")
    }
    for (file in files) {
        println("${file.name} ${file.id}")
    }
}