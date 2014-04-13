package com.ivan.nikolov.actor

import akka.actor.Actor
import com.ivan.nikolov.routes.PingRoute
import com.ivan.nikolov.app.ComponentRegistry._

/**
 * The Scala API actor.
 */
class ScalaApiActor extends Actor with PingRoute {

  val settingsDep = settings

  implicit def actorRefFactory = context

  def receive = runRoute(pingRoute)
}
