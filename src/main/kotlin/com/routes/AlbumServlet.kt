package com.routes

import com.util.GoogleDriveService
import com.view.albums
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AlbumServlet : HttpServlet() {


    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        var list = GoogleDriveService.getAlbums()
        resp.writer.println(albums(list))
    }

    override fun doPost(req: HttpServletRequest, resp: HttpServletResponse) {
        doGet(req, resp)
    }
}