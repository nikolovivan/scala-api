package com.ivan.nikolov

import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.ServletContextHandler
import spray.servlet.{Servlet30ConnectorServlet, Initializer}
import com.ivan.nikolov.app.ComponentRegistry._

/**
 * Starts the embedded Jetty server that runs the given application.
 */
object JettyStart {

  def main(args: Array[String]) {
    start()
  }

  /**
   * Starts the embedded Jetty instance. This is similar to the
   * web.xml contents here.
   */
  def start() {
    try {
      val server = new Server(settings.Application.port)

      val contextHandler = new ServletContextHandler(ServletContextHandler.NO_SESSIONS)
      contextHandler.setContextPath("/")
      server.setHandler(contextHandler)
      contextHandler.addEventListener(new Initializer)

      contextHandler.addServlet(classOf[Servlet30ConnectorServlet].getName, "/*")

      server.start()
      server.join()
    } catch {
      case e: Throwable => e.printStackTrace()
    }
  }
}
