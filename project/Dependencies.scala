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

import sbt._
import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._

trait Dependencies {

  object Versions {

    val bloomer = "0.6.3"
    val bulma = "0.6.2"
    val fontAwesome = "4.7.0"
    val macwire = "2.3.1"
    val react = "16.2.0"
    val scala = "2.12.5"
    val scalaJSReact = "1.2.0"
  }

  val bloomer = "bloomer" -> Versions.bloomer
  val bulma = "bulma" -> Versions.bulma
  val fontAwesome = "font-awesome" -> Versions.fontAwesome
  val macwire = "com.softwaremill.macwire" %% "macros" % Versions.macwire % Provided
  val react = "react" -> Versions.react
  val reactDom = "react-dom" -> Versions.react
  val scalaJSReact = Def.setting("com.github.japgolly.scalajs-react" %%% "core" % Versions.scalaJSReact)
}