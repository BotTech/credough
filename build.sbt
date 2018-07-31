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

scalaVersion in Global := "2.12.6"

licenses in ThisBuild += "Apache-2.0" -> new URL("https://www.apache.org/licenses/LICENSE-2.0.txt")
organizationName in ThisBuild := "BotTech"
startYear in ThisBuild := Some(2018)

lazy val root = (project in file("."))
  .settings(name := "credough")
  .aggregate(
    ui
  )

lazy val ui = (project in file("ui"))
  .enablePlugins(
    ScalaJSBundlerPlugin,
    ScalaJSPlugin
  )
  .settings(
    libraryDependencies ++= Seq(
      "com.apollographql" %%% "apollo-scalajs-core" % "0.4.0",
      "com.apollographql" %%% "apollo-scalajs-react" % "0.4.0",
      "me.shadaj" %%% "slinky-hot" % "0.4.1",
      "me.shadaj" %%% "slinky-web" % "0.4.1"
    ),
    scalacOptions += "-P:scalajs:sjsDefinedByDefault",
    scalaJSUseMainModuleInitializer := true,
    addCompilerPlugin("org.scalameta" % "paradise" % "3.0.0-M11" cross CrossVersion.full)
  ).settings(inConfig(Compile)(bundlerSettings))

def bundlerSettings: Seq[Def.Setting[_]] = Seq(
  useYarn := true,
  webpackBundlingMode := BundlingMode.LibraryOnly(),
  npmDependencies ++= Seq(
    "bloomer" -> "0.6.3",
    "bulma" -> "0.6.2",
    "react" -> "16.2.0",
    "react-dom" -> "16.2.0",
    "react-proxy" -> "1.1.8",
    "apollo-boost" -> "0.1.12",
    "react-apollo" -> "2.1.9",
    "graphql" -> "0.13.2",
    "graphql-tag" -> "2.9.2"
  ),
  npmDevDependencies += "apollo" -> "1.6.0"
)
