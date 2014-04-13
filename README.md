scala-api
=========

A simple api written in Scala using the Spray Framework.

Features
--------

The API includes the following libraries:

1.  Spray.IO: This is the core of the API that allows users to generate API services.
    The main components of spray.io are the **spray-servlet**, **spray-routing** and all the **Akka**-related dependencies.
    Additionally we have added support for caching and json marshalling and unmarshalling using the **spray-caching** and **spray-json** dependencies.
2.  Jetty: This is the application container.
3.  Typesafe config: The application and the underlying **Akka** classes use the typesafe config system for configuration.
4.  Slick: This is a library for database query and access that supports various underlying DBMSs. The user is free to use whatever they want and add the required connector later.
5.  Testing tools: **Scalatest** is the preferred testing tool, together with **Mockito**.
6.  **sbt-assembly**: For creating a fat jar with embedded Jetty for distribution.
7.  **sbt-release**: For defining custom release steps.

Starting the Server
-------------------

Starting the server is relatively easy. There are two ways of doing this:

1.  Without building the fat jar.
2.  Building the fat jar and running it.

### Running the Application Through SBT

To run the application through SBT, run the following command:

    sbt container:start shell

It might take some time to compile if you haven't done this yet and then you will see a shell. Then your service will become available and you will be able to query it through the browser.

To stop the server, run:

    container:stop

### Running the Server Through a Fat JAR

For this operation you will need to create the fat jar first and then simply start it as normal Java application.

To build the fat jar:

    sbt assembly

To run the application:

    java -jar jar-name.jar

Then to stop the application, just press Ctrl+C.

Conclusion
----------

This is the skeleton application you can use to quickly start your development. From then on, it is up to you to add the dependencies you need and make the API do whatever you want.
