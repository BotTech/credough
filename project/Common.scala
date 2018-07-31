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

import Dependencies._
import sbt.Keys._
import sbt.{Def, _}

object Common extends AutoPlugin {

  override val trigger: PluginTrigger = allRequirements

  override val globalSettings: Seq[Def.Setting[_]] = Seq(
    scalaVersion := Versions.scala
  )

  private val `apache-2.0` = ("Apache-2.0", new URL("https://www.apache.org/licenses/LICENSE-2.0.txt"))

  override val buildSettings: Seq[Def.Setting[_]] = Seq(
    licenses += `apache-2.0`,
    organizationName := "BotTech",
    startYear := Some(2018)
  )
}
