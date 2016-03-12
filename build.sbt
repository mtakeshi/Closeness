name := "rest"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
    "io.spray" %% "spray-can" % "1.3.3",
    "io.spray" %% "spray-http" % "1.3.3",
    "io.spray" %% "spray-routing" % "1.3.1",
    "com.typesafe.akka" %% "akka-actor" % "2.4.2",
    "com.typesafe.akka" %% "akka-slf4j" % "2.4.2",
    "net.liftweb" %% "lift-json" % "2.6.3"
)

resolvers ++= Seq(
    "Spray repository" at "http://repo.spray.io",
    "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"
)

