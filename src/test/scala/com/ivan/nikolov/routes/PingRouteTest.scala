package com.ivan.nikolov.routes

import org.scalatest.{ShouldMatchers, FlatSpec}
import spray.testkit.ScalatestRouteTest
import com.ivan.nikolov.app.TestEnvironment
import spray.http.StatusCodes
import spray.httpx.SprayJsonSupport
import spray.json.DefaultJsonProtocol

/**
 * Created by volcom on 13/04/14.
 */
class PingRouteTest extends FlatSpec with ShouldMatchers with ScalatestRouteTest with PingRoute with TestEnvironment with SprayJsonSupport with DefaultJsonProtocol {
  implicit def actorRefFactory = system
  val settingsDep = settings

  "PingRoute" should "return a greeting for GET requests to the ping/html path." in {
    Get("/ping/html") ~> pingRoute ~> check {
      responseAs[String] should include("The application works correctly on port %d".format(settingsDep.Application.port))
    }
  }

  it should "return a json response for the ping path." in {
    Get("/ping") ~> pingRoute ~> check {
      val responseMap = responseAs[Map[String, String]]
      responseMap.keySet should contain("code")
      responseMap.keySet should contain("message")
      responseMap.values should contain("200")
      responseMap.values should contain("The application works correctly on port %d!".format(settingsDep.Application.port))
    }
  }

  it should "return a json response for the ping/ path (with trailing slash)." in {
    Get("/ping/") ~> pingRoute ~> check {
      val responseMap = responseAs[Map[String, String]]
      responseMap.keySet should contain("code")
      responseMap.keySet should contain("message")
      responseMap.values should contain("200")
      responseMap.values should contain("The application works correctly on port %d!".format(settingsDep.Application.port))
    }
  }

  it should "return a json response for the ping/json path." in {
    Get("/ping/json") ~> pingRoute ~> check {
      val responseMap = responseAs[Map[String, String]]
      responseMap.keySet should contain("code")
      responseMap.keySet should contain("message")
      responseMap.values should contain("200")
      responseMap.values should contain("The application works correctly on port %d!".format(settingsDep.Application.port))
    }
  }

  it should "leave GET requests to unknown paths unhandled." in {
    Get("/something") ~> pingRoute ~> check {
      handled should equal(false)
    }
  }

  it should "return a MethodNotAllowed for any requests other than GET." in {
    Put("/ping/json") ~> sealRoute(pingRoute) ~> check {
      status should equal(StatusCodes.MethodNotAllowed)
      responseAs[String] should equal("HTTP method not allowed, supported methods: GET")
    }
  }
}
