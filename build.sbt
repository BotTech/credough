import com.typesafe.sbt.web.PathMapping
import sbt.Def

logLevel := Level.Debug

val scalaJSProjectRefs = Def.settingKey[Seq[ProjectReference]]("Scala.js projects attached to the sbt-web project")
val npmAssetDependencies = settingKey[Seq[String]]("NPM asset dependencies (assets that your program uses)")

lazy val root = (project in file("."))
  .aggregate(
    ui,
    server
  )

lazy val server = (project in file("server"))
  .enablePlugins(
    WebScalaJSBundlerPlugin
  ).settings(
    scalaJSProjectRefs := Seq(ui),
    scalaJSProjects := Seq(ui)
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
