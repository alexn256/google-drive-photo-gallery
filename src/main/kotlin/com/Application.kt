package com

import com.routes.AlbumServlet
import com.routes.PhotoServlet
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.ServletHandler


fun main() {
    val server = Server(8090)
    val servletHandler = ServletHandler()
    servletHandler.addServletWithMapping(AlbumServlet::class.java, "/albums")
    servletHandler.addServletWithMapping(PhotoServlet::class.java, "/photos")
    server.handler = servletHandler
    server.start()
    server.join()
}