import sbt._

object Dependencies {
  object akka {
    private val version = "2.6.8"
    private val httpVersion = "10.2.0"

    val actor = "com.typesafe.akka" %% "akka-actor-typed" % version
    val stream = "com.typesafe.akka" %% "akka-stream-typed" % version
    val http = "com.typesafe.akka" %% "akka-http" % httpVersion
  }

  val all = Seq(
    akka.actor,
    akka.http,
    akka.stream
  )
}
