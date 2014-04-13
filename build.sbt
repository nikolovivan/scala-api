organization := "com.ivan.nikolov"

name := "scala-api"

scalaVersion := "2.10.3"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

javaOptions ++= Seq("-target", "1.7", "-source", "1.7")

resolvers ++= Seq(
  "spray repo" at "http://repo.spray.io"
)

publishMavenStyle := true

libraryDependencies ++= {
  val akkaVersion = "2.3.0"
  val sprayVersion = "1.3.1"
  val sprayJsonVersion = "1.2.6"
  val typesafeVersion = "1.2.0"
  val slickVersion = "2.0.1"
  Seq(
    "org.scalatest" % "scalatest_2.10" % "2.1.3" % "test",
    "io.spray" % "spray-servlet" % sprayVersion,
    "io.spray" % "spray-routing" % sprayVersion,
    "io.spray" % "spray-caching" % sprayVersion,
    "io.spray" %%  "spray-json" % sprayJsonVersion,
    "io.spray" % "spray-testkit" % sprayVersion % "test",
    "org.eclipse.jetty" % "jetty-webapp" % "9.1.0.v20131115" % "container",
    "org.eclipse.jetty" % "jetty-plus" % "9.1.0.v20131115" % "container",
    "org.eclipse.jetty.orbit" % "javax.servlet" % "3.0.0.v201112011016" % "container" artifacts Artifact("javax.servlet", "jar", "jar"),
    "org.eclipse.jetty" % "jetty-server" % "9.1.3.v20140225",
    "org.eclipse.jetty" % "jetty-servlet" % "9.1.3.v20140225",
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-testkit" % akkaVersion % "test",
    "com.typesafe" % "config" % typesafeVersion, // configuration.
    "com.typesafe.slick" %% "slick" % slickVersion, // for database connectivity.
    "org.scala-tools.testing" % "specs_2.9.1" % "1.6.9" % "test", // for the mockito sugar
    "org.mockito" % "mockito-all" % "1.9.5" % "test" // mockito for tests
  )
}

// Import the web settings.
seq(webSettings: _*)

port in container.Configuration := 8888

//ssl in container.Configuration := Some("192.168.1.4", 8443, "keystore_path", "keystore_password", "key_password")