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

import org.scalajs.dom
import org.scalajs.dom.Element
import slinky.hot
import slinky.web.ReactDOM

import scala.scalajs.LinkingInfo

// TODO: Import some CSS
//@JSImport("resources/index.css", JSImport.Default)
//@js.native
//object IndexCSS extends js.Object

object Main {

  def main(args: Array[String]): Unit = {
    if (LinkingInfo.developmentMode) {
      hot.initialize()
    }
    val container = root().getOrElse(createRoot())
    val _ = ReactDOM.render(HelloApp(), container)
  }

  private def root(): Option[Element] = {
    Option(dom.document.getElementById("root"))
  }

  private def createRoot(): Element = {
    val elem = dom.document.createElement("div")
    elem.id = "root"
    dom.document.body.appendChild(elem)
    elem
  }
}
