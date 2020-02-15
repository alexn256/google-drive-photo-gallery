package com

import com.util.GoogleDriveService

fun main() {
    val service = GoogleDriveService()
    val albums = service.getAlbums()
    print(albums)
}