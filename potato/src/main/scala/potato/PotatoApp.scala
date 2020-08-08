package potato

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import scala.io.StdIn
import scala.concurrent.ExecutionContext

object PotatoApp extends App {

  implicit val system = ActorSystem(Behaviors.empty, "potato")
  implicit val executionContext: ExecutionContext = system.executionContext

  private val route: Route =
    path("health") {
      get {
        complete(
          HttpEntity(ContentTypes.`application/json`, """{"status":"ok"}""")
        )
      }
    }

  private val interface = "0.0.0.0"
  private val port = 8080
  private val serverFuture = Http().newServerAt(interface, port).bind(route)

  println(s"server at: ${interface}:${port}")
  StdIn.readLine()
  serverFuture
    .flatMap(_.unbind())
    .onComplete(_ => system.terminate())
}
