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

lazy val root = (project in file("."))
  .settings(name := "credough")
  .aggregate(
    ui,
    server
  )

lazy val server = (project in file("server"))
  .enablePlugins(
    Play,
    WebScalaJSBundlerPlugin,
    GraphQLSchemaPlugin
  )
  .settings(
    libraryDependencies ++= Seq(
      macwire,
      sangria,
      sangriaPlayJson
    ),
    TwirlKeys.templateImports := Nil,
    graphqlSchemaSnippet := "models.schema",
    scalaJSProjects += ui,
    Assets / pipelineStages ++= Seq(scalaJSPipeline),
    Compile / compile := (Compile / compile).dependsOn(scalaJSPipeline).value
  )


lazy val ui = (project in file("ui"))
  .enablePlugins(
    ScalaJSBundlerPlugin,
    ScalaJSPlugin,
    ScalaJSWeb
  )
  .settings(
    graphQLTypesNamespace := "nz.co.bottech.credough.graphql",
    libraryDependencies ++= ScalaJS.dependencies.value,
    scalacOptions += "-P:scalajs:sjsDefinedByDefault",
    scalaJSUseMainModuleInitializer := true,
    addCompilerPlugin(macroParadise)
  ).settings(inConfig(Compile)(bundlerSettings))

def bundlerSettings: Seq[Def.Setting[_]] = Seq(
  npmDependencies ++= npm.commonJSModules,
  useYarn := true,
  webpackBundlingMode := BundlingMode.LibraryOnly(),
  npmDependencies ++= npm.apolloClientCommonJSModules,
  npmDevDependencies += npm.apollo,
  graphQLApolloCLI := npmUpdate.value / "node_modules" / ".bin" / "apollo"
)
