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

import play.sbt.routes.RoutesKeys

lazy val root = (project in file("."))
  .settings(name := "credough")
  .aggregate(
    ui,
    server
  )

lazy val server = (project in file("server"))
  .enablePlugins(
    AutomateHeaderPlugin,
    PlayScala,
    WebBackend
  )
  .settings(
    libraryDependencies ++= Seq(
      macwire,
      sangria,
      sangriaPlayJson
    ),
    TwirlKeys.templateImports := Nil,
    RoutesKeys.routesImport := Nil,
    RoutesKeys.routesImport += "controllers.Assets.Asset",
    graphqlSchemaSnippet := "models.StarWarsSchemaDefinition.StarWarsSchema"
  )

lazy val ui = (project in file("ui"))
  .enablePlugins(
    AutomateHeaderPlugin,
    WebFrontend
  )
  .settings(
    graphQLTypesNamespace := "nz.co.bottech.credough.graphql"
  )
