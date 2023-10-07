package io.github.windymelt.ogimagekun

import cats.effect.{IO, IOApp}

object Main extends IOApp.Simple:
  override def run: IO[Unit] =
    OgimagekunServer.run
