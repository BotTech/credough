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

import ApolloGraphQL.Keys._
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

    val npmAssetDependencies = settingKey[Seq[Npm.Assets]]("NPM asset dependencies (assets that your program uses)")
  }

  object Keys extends Keys

  import Keys._

  val autoImport: Keys = Keys

  override val requires: Plugins = ScalaJSBundlerPlugin && ScalaJSPlugin && ScalaJSWeb && ApolloGraphQL

  val ignoreWartremoverOptions = Set(
    "-P:wartremover:traverser:org.wartremover.warts.AsInstanceOf",
    "-P:wartremover:traverser:org.wartremover.warts.NonUnitStatements",
    "-P:wartremover:traverser:org.wartremover.warts.Null"
  )

  override val projectSettings: Seq[Def.Setting[_]] = Seq(
    libraryDependencies ++= ScalaJS.dependencies.value,
    // TODO: Remove this when we upgrade to Scala.js 1.x
    scalacOptions += "-P:scalajs:sjsDefinedByDefault",
    scalacOptions := scalacOptions.value.filterNot(ignoreWartremoverOptions.contains),
    // TODO: Is this the best way?
    scalaJSUseMainModuleInitializer := true,
    addCompilerPlugin(macroParadise),
    npmAssetDependencies := npm.assets
  ) ++ inConfig(Compile)(bundlerSettings)

  def bundlerSettings: Seq[Def.Setting[_]] = Seq(
    npmDependencies ++= npm.commonJSModules,
    npmDependencies ++= npmAssetDependencies.value,
    npmDependencies ++= npm.apolloClientCommonJSModules,
    npmDevDependencies += npm.apollo,
    useYarn := true,
    // Process only the entrypoints via webpack and produce a library of dependencies.
    webpackBundlingMode := BundlingMode.LibraryOnly(),
    graphQLApolloCLI := npmUpdate.value / "node_modules" / ".bin" / "apollo"
  )
}
