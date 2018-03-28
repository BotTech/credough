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

// Scala.js
addSbtPlugin("org.scala-js" % "sbt-scalajs" % "0.6.22")

// Scala.js for sbt-web
addSbtPlugin("com.vmunier" % "sbt-web-scalajs" % "1.0.6")

// GZip
addSbtPlugin("com.typesafe.sbt" % "sbt-gzip" % "1.0.2")

// Digest
addSbtPlugin("com.typesafe.sbt" % "sbt-digest" % "1.1.4")

// sbt-web Scala.js Bundler
addSbtPlugin("ch.epfl.scala" % "sbt-web-scalajs-bundler" % "0.11.0")
