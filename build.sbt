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

//import com.typesafe.sbt.web.PathMapping
import Dependencies._

lazy val root = (project in file("."))
  .settings(name := "credough")
  .aggregate(
    ui,
    server
  )

//val foo = taskKey[Classpath]("Foo")
//val foo2 = taskKey[File]("Foo")
//val foo3 = taskKey[Seq[PathMapping]]("Foo")
//val foo4 = settingKey[File]("Foo")

lazy val server = (project in file("server"))
  .enablePlugins(
    Play,
    WebBackend
  )
  .settings(
    libraryDependencies ++= Seq(
      macwire,
      sangria,
      sangriaPlayJson
    ),
    TwirlKeys.templateImports := Nil,
    graphqlSchemaSnippet := "models.schema",
//    foo := (Assets / exportedProducts).value,
//    foo2 := (Assets / WebKeys.exportedAssets).value,
//    foo3 := (Assets / WebKeys.exportedMappings).value,
//    foo4 := (Assets / classDirectory).value,
    scalaJSProjects += ui
  )


lazy val ui = (project in file("ui"))
  .enablePlugins(
    WebFrontend
  )
  .settings(
    graphQLTypesNamespace := "nz.co.bottech.credough.graphql"
  )
