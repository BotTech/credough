/*
 * Copyright 2018 BotTech
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.timushev.sbt.updates.UpdatesPlugin.autoImport._
import org.scalastyle.sbt.ScalastylePlugin.autoImport._
import sbt.Keys._
import sbt.{Def, _}
import wartremover._
import xsbti.compile.CompileAnalysis

object Common extends AutoPlugin {

  object Import extends Dependencies {

    val compileThenCheckStyle = taskKey[CompileAnalysis]("Compiles sources and then runs scalastyle on your code")
  }

  import Import._

  val autoImport: Import.type = Import

  override val trigger: PluginTrigger = allRequirements

  override val globalSettings: Seq[Def.Setting[_]] = Seq(
    scalaVersion := Versions.scala
  )

  override val buildSettings: Seq[Def.Setting[_]] = Seq(
    licenses += ("Apache-2.0" -> new URL("https://www.apache.org/licenses/LICENSE-2.0.txt")),
    organizationName := "BotTech",
    startYear := Some(2018) // scalastyle:ignore
  )

  override val projectSettings: Seq[Def.Setting[_]] = {
    Seq(
      dependencyUpdatesFailBuild := true,
      scalastyleFailOnWarning := true,
      wartremoverErrors ++= Warts.unsafe,
      Compile / console / scalacOptions := (console / scalacOptions).value.filterNot(_.contains("wartremover"))
    ) ++
      inConfig(Compile)(compileSettings) ++
      inConfig(Test)(compileSettings)
  }

  private def compileSettings = Seq(
    compileThenCheckStyle := Def.taskDyn {
      val analysis = compile.value
      Def.task {
        val _ = scalastyle.toTask("").value
        analysis
      }
    }.value
  )
}
