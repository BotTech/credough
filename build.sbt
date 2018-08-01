import sbt.Def

logLevel := Level.Debug

lazy val root = (project in file("."))
  .aggregate(
    ui,
    server
  )

lazy val server = (project in file("server"))
  .enablePlugins(
    WebScalaJSBundlerPlugin
  )

lazy val ui = (project in file("ui"))
  .enablePlugins(
    ScalaJSBundlerPlugin
  ).settings(
    sourceGenerators in Compile += Def.task {
      val _ = (npmUpdate in Compile).value
      Seq.empty[File]
    }
  )
