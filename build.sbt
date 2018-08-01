import com.typesafe.sbt.web.PathMapping
import sbt.Def
import sbt.internal.LoadedBuildUnit

logLevel := Level.Debug

val scalaJSProjectRefs = Def.settingKey[Seq[ProjectReference]]("Scala.js projects attached to the sbt-web project")
val npmAssetDependencies = settingKey[Seq[Dependencies.Npm.Assets]]("NPM asset dependencies (assets that your program uses)")

lazy val root = (project in file("."))
  .aggregate(
    ui,
    server
  )

lazy val server = (project in file("server"))
  .enablePlugins(
    PlayScala,
    WebScalaJSBundlerPlugin
  ).settings(
    scalaJSProjectRefs := frontendProjectsSetting.value,
    scalaJSProjects := scalaJSProjectsSetting.value,
    npmAssets ++= frontendNpmAssetsTask.value
  )

lazy val ui = (project in file("ui"))
  .enablePlugins(
    ScalaJSBundlerPlugin,
    ApolloGraphQL
  ).settings(
    graphQLApolloCLI in Compile := (npmUpdate in Compile).value
  )

def frontendProjectsSetting: Def.Initialize[Seq[ProjectRef]] = Def.setting {
  val units = loadedBuild.value.units
  val projects = allProjectRefs(units)
  for {
    projectRef <- projects
    project <- Project.getProject(projectRef, units).toList
    autoPlugin <- project.autoPlugins if autoPlugin == ApolloGraphQL
  } yield projectRef
}

def scalaJSProjectsSetting: Def.Initialize[Seq[Project]] = Def.setting {
  val units = loadedBuild.value.units
  val projects = scalaJSProjectRefs.value
  for {
    projectRef <- projects
    project <- projectForReference(projectRef, units).toList
  } yield Project(project.id, project.base)
}

def projectForReference(ref: Reference, units: Map[URI, LoadedBuildUnit]): Option[ResolvedProject] = {
  ref match {
    case projectRef: ProjectRef => Project.getProject(projectRef, units)
    case _ => None
  }
}

def allProjectRefs(units: Map[URI, LoadedBuildUnit]): Seq[ProjectRef] = {
  units.toSeq.flatMap {
    case (build, unit) => refs(build, unit.defined.values.toSeq)
  }
}

def refs(build: URI, projects: Seq[ResolvedProject]): Seq[ProjectRef] = {
  projects.map { p =>
    ProjectRef(build, p.id)
  }
}

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
      case (pathFinder, asset) => pathFinder +++ asset.assets(nodeModulesDir / asset.name)
    }
    assets.pair(Path.relativeTo(nodeModulesDir))
  }
}
