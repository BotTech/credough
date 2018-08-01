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

import sbt.Keys._
import sbt.{Def, _}

object ApolloGraphQL extends AutoPlugin {

  trait Keys {

    val graphQLApolloCLI = Def.taskKey[File]("Path to the apollo CLI")
  }

  object Keys extends Keys

  import Keys._

  val autoImport: Keys = Keys

  override val projectSettings: Seq[Def.Setting[_]] = {
      inConfig(Compile)(graphQLSettings)
  }

  private def graphQLSettings = Seq(
    sourceGenerators += generateGraphQLTypesTask
  )

  private def generateGraphQLTypesTask = Def.task {
    val _ = graphQLApolloCLI.value.getPath
    Seq.empty[File]
  }
}
