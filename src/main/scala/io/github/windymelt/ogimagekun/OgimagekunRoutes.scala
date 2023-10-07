package io.github.windymelt.ogimagekun

import cats.effect.IO
import org.http4s.HttpRoutes
import org.http4s.MediaType
import org.http4s.Request
import org.http4s.StaticFile
import org.http4s.dsl.io._
import org.http4s.headers.`Content-Type`
import org.http4s.server.staticcontent._

object OgimagekunRoutes:
  def static(file: String, request: Request[IO]) =
    StaticFile.fromResource("/" + file, Some(request)).getOrElseF(NotFound())

  def indexRoutes: HttpRoutes[IO] = HttpRoutes.of[IO] {
    case GET -> Root / title =>
      Ok(
        html.ogp(title).toString,
        `Content-Type`(MediaType.text.html)
      )
  }

  val ogpRoutes: HttpRoutes[IO] = HttpRoutes.of[IO] {
    case req @ GET -> Root / "ogp.svg" =>
      static("ogp.svg", req)
  }
