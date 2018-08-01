import sbt.Def

lazy val root = (project in file("."))
  .aggregate(ui)

lazy val ui = (project in file("ui"))
  .enablePlugins(
    ScalaJSBundlerPlugin
  ).settings(
    sourceGenerators in Compile += Def.task {
      val _ = (npmUpdate in Compile).value
      Seq.empty[File]
    }
  )
