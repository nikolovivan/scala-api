package com.ivan.nikolov

import spray.servlet.WebBoot
import akka.actor.{Props, ActorSystem}
import com.ivan.nikolov.actor.ScalaApiActor

/**
 * The boot class the initialized the actor system and assigns the
 * service actor. It is instantiated by the servlet initializer.
 */
class Boot extends WebBoot {
  val system = ActorSystem("scala-api")

  val serviceActor = system.actorOf(Props[ScalaApiActor])
}
