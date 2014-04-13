package com.ivan.nikolov.routes

import spray.routing.HttpService
import spray.http.MediaTypes._
import com.ivan.nikolov.config.SettingsComponent
import spray.httpx.SprayJsonSupport
import spray.json.DefaultJsonProtocol

/**
 * The PING route that provides some testing functions for pinging.
 */
trait PingRoute extends HttpService with SprayJsonSupport with DefaultJsonProtocol {

  // dependency injection for the routes happens in the actors that use them.
  // In other classes like services - with the cake pattern, e. g. this: Component => ...
  val settingsDep: SettingsComponent#Settings

  val jsonResponse =
    get {
      respondWithMediaType(`application/json`) {
        complete {
          // Map[String, String]. Otherwise doesn't work with spray-json.
          Map("code" -> "200", "message" -> "The application works correctly on port %d!".format(settingsDep.Application.port))
        }
      }
    }

  val pingRoute =
    pathPrefix("ping") {
      pathEndOrSingleSlash {
        jsonResponse
      } ~
      path("html") {
        get {
          respondWithMediaType(`text/html`) {
            complete {
              <html>
                <head>
                  <title>Works!</title>
                </head>
                <body>
                  <h1>The application works correctly on port {settingsDep.Application.port} with <i>spray-routing</i> and <i>Jetty</i>!</h1>
                </body>
              </html>
            }
          }
        }
      } ~
      path("json") {
        jsonResponse
      }
    }
}
