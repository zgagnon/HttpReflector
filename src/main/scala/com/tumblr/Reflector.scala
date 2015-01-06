package main.scala.com.tumblr

import colossus.IOSystem
import colossus.protocols.http.{ HttpHead, Http }
import colossus.protocols.http.HttpMethod.Get
import colossus.service.Service
import com.typesafe.config.ConfigFactory

/**
 * Created by zoe on 1/5/15.
 */
object Reflector extends App {

  implicit class HeaderString(header: HttpHead) {
    def mkString: String =
      s"${header.method.name} ${header.version.toString()}\n" +
        s"${header.url}\n" +
        s"${header.headers map { i: (String, String) => s"${i._1}: ${i._2}" } mkString ("\n")}"
  }

  import colossus.protocols.http.UrlParsing.Strings._

  import scala.concurrent.duration._

  implicit val io = IOSystem()

  val config = ConfigFactory.load

  Service.serve[Http]("http-service", 8000, 2 seconds) {
    context =>
      val client = context.clientFor[Http]("localhost", 8000)
      client
      context.handle {
        connection =>
          connection.become {
            case request @ Get on Root /: rest =>
              request.ok(request.head.mkString)
          }
      }
  }
}
