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

import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._
import sbt._

trait Dependencies {

  object Versions {

    val bloomer = "0.6.3"
    val bulma = "0.6.2"
    val fontAwesome = "4.7.0"
    val macroParadise = "3.0.0-M11"
    val macwire = "2.3.1"
    val react = "16.2.0"
    val reactProxy = "1.1.8"
    // TODO: Update to 2.12.5 once there is macro paradise support
    val scala = "2.12.4"
    val slinky = "0.3.2"
  }

  val npm: Npm.type = Npm

  object Npm {

    sealed trait NpmDependency {
      def name: String
      def version: String
    }

    object NpmDependency {
      implicit def toTuple(npmDependency: NpmDependency): (String, String) = npmDependency.name -> npmDependency.version
    }

    // TODO: These might need some more thought
    case class CommonJSModule(name: String, version: String) extends NpmDependency
    case class Assets(name: String, version: String, assets: File => PathFinder = _.allPaths) extends NpmDependency

    val bloomer = CommonJSModule("bloomer", Versions.bloomer)
    val bulma = CommonJSModule("bulma", Versions.bulma)
    val react = CommonJSModule("react", Versions.react)
    val reactDom = CommonJSModule("react-dom", Versions.react)
    val reactProxy = CommonJSModule("react-proxy", Versions.reactProxy)

    val commonJSModules = Seq(
      bloomer,
      bulma,
      react,
      reactDom,
      reactProxy
    )

    val fontAwesome = Assets("font-awesome", Versions.fontAwesome)

    val assets = Seq(
      fontAwesome
    )
  }

  object ScalaJS {

    val slinkyHot = Def.setting("me.shadaj" %%% "slinky-hot" % Versions.slinky)
    val slinkyWeb = Def.setting("me.shadaj" %%% "slinky-web" % Versions.slinky)

    val dependencies = Def.setting {
      Seq(
        slinkyHot.value,
        slinkyWeb.value
      )
    }
  }

  // This is the one from org.scalameta not org.scalamacros
  // See https://github.com/scalacenter/advisoryboard/pull/30 for when we can transition
  val macroParadise = "org.scalameta" % "paradise" % Versions.macroParadise cross CrossVersion.full
  val macwire = "com.softwaremill.macwire" %% "macros" % Versions.macwire % Provided
}

object Dependencies extends Dependencies
