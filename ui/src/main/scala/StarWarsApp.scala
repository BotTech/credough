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

import slinky.hot

import scala.scalajs.LinkingInfo
import scala.scalajs.js.undefined
import scala.scalajs.js.annotation.JSExportTopLevel
import scala.scalajs.js.Dynamic.global

object StarWarsApp extends ReactGraphQLApp {

  @JSExportTopLevel("entrypoint.main")
  def main(args: Array[String]): Unit = {
    if (LinkingInfo.developmentMode) {
      hot.initialize()
    }
    val graphQLURL = global.environment.controllers.StarWarsController.graphql("", undefined, undefined).toString
    val graphIQLURL = global.environment.controllers.StarWarsController.graphiql().url.toString
    val sangriaImageURL = global.environment.controllers.Assets.versioned("images/sip-of-sangria-1.svg").url.toString
    val props = StarWarsAppComponent.Props(sangriaImageURL, graphIQLURL)
    val app = StarWarsAppComponent(props)
    val _  = renderApp(app, graphQLURL)
  }
}
