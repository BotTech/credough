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
    scalaJSProjects := Seq(ui),
    npmAssets ++= frontendNpmAssetsTask.value
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

def frontendNpmAssetsTask: Def.Initialize[Task[Seq[PathMapping]]] = {
  frontendProjectAssetsTask.flatMap { tasks =>
    tasks.fold(task(Nil)) { (previous, next) =>
      for {
        p <- previous
        n <- next
      } yield p ++ n
    }
  }
}

def frontendProjectAssetsTask: Def.Initialize[Task[Seq[Task[Seq[PathMapping]]]]] = Def.task {
  val stateTask = state.taskValue
  val projectRefs = scalaJSProjectRefs.value
  projectRefs.map { project =>
    projectAssets(stateTask, Scope.ThisScope.in(project))
  }
}

def projectAssets(stateTask: Task[State], scope: Scope): Task[Seq[(File, String)]] = {
  for {
    state <- stateTask
    extracted = Project.extract(state)
    assetDependencies <- task(extracted.get(scope / npmAssetDependencies))
    nodeInstallDir <- extracted.get(scope.in(Compile) / npmUpdate)
  } yield {
    val nodeModulesDir = nodeInstallDir / "node_modules"
    val assets = assetDependencies.foldLeft(PathFinder.empty) {
      case (pathFinder, _) => pathFinder
    }
    assets.pair(Path.relativeTo(nodeModulesDir))
  }
}
