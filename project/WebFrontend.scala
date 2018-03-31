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

import Dependencies.Npm.CommonJSModule
import Dependencies._
import org.scalajs.sbtplugin.ScalaJSPlugin
import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._
import sbt.Keys._
import sbt.{Def, _}
import scalajsbundler.sbtplugin.ScalaJSBundlerPlugin
import scalajsbundler.sbtplugin.ScalaJSBundlerPlugin.autoImport._
import webscalajs.ScalaJSWeb

object WebFrontend extends AutoPlugin {

  trait Keys {
    val npmCommonJSDependencies = settingKey[Seq[CommonJSModule]]("NPM CommonJS dependencies (modules that your program uses)")
    val npmAssetDependencies = settingKey[Seq[Npm.Assets]]("NPM asset dependencies (assets that your program uses)")
  }

  object Import extends Keys

  import Import._

  val autoImport: Import.type = Import

  override val requires: Plugins = ScalaJSBundlerPlugin && ScalaJSPlugin && ScalaJSWeb

  override val projectSettings: Seq[Def.Setting[_]] = Seq(
    libraryDependencies ++= ScalaJS.dependencies.value,
    npmCommonJSDependencies := npm.commonJSModules,
    npmAssetDependencies := npm.assets,
    // TODO: Remove this when we upgrade to version 1.x
    scalacOptions += "-P:scalajs:sjsDefinedByDefault",
    // TODO: Is this the best way?
    scalaJSUseMainModuleInitializer := true,
    // Enable macro paradise for Slinky macro annotations.
    addCompilerPlugin(macroParadise)
  ) ++ bundlerSettings

  def bundlerSettings: Seq[Def.Setting[_]] = Seq(
    // Client dependencies from npm.
    npmDependencies in Compile ++= npmCommonJSDependencies.value ++ npmAssetDependencies.value,
    // Use Yarn instead of npm.
    useYarn := true,
    // Process only the entrypoints via webpack and produce a library of dependencies.
    webpackBundlingMode := BundlingMode.LibraryOnly()
  )
}
