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

// Scalastyle
addSbtPlugin("org.scalastyle" %% "scalastyle-sbt-plugin" % "1.0.0")

// Wartremover
addSbtPlugin("org.wartremover" % "sbt-wartremover" % "2.3.1")

// OWASP Dependency Check
addSbtPlugin("net.vonbuchholtz" % "sbt-dependency-check" % "0.2.2")

// sbt Header
addSbtPlugin("de.heikoseeberger" % "sbt-header" % "5.0.0")

// Recommended Scalac Flags
addSbtPlugin("io.github.davidgregory084" % "sbt-tpolecat" % "0.1.3")
