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

import org.scalastyle.sbt.ScalastylePlugin.autoImport._
import sbt.Keys._
import sbt.{Def, _}
import xsbti.compile.CompileAnalysis

object Common extends AutoPlugin {

  val compileThenCheckStyle = taskKey[CompileAnalysis]("Compiles sources and then runs scalastyle on your code")

  override def trigger: PluginTrigger = allRequirements

  override def buildSettings: Seq[Def.Setting[_]] = Seq(
    organizationName := "BotTech",
    startYear := Some(2018),
    licenses += ("Apache-2.0", new URL("https://www.apache.org/licenses/LICENSE-2.0.txt"))
  )

  override def projectSettings: Seq[Def.Setting[_]] = {
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
