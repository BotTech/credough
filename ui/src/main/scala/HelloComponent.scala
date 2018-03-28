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

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

object HelloComponent {

  case class Props(name: String)

  val Hello =
    ScalaComponent.builder[Props]("Hello")
      .render_P(props => <.div("Hello there ", props.name))
      .build

  def apply(name: String) = Hello(Props(name))
}
