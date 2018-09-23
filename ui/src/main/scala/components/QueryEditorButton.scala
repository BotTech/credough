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

package components

import slinky.core.annotations.react
import slinky.core.facade.ReactElement
import slinky.core.{CustomAttribute, StatelessComponent}
import slinky.web.html._

@react class QueryEditorButton extends StatelessComponent {

  case class Props(text: String, section: String, expanded: Boolean)

  val dataToggle = new CustomAttribute[String]("data-toggle")
  val dataTarget = new CustomAttribute[String]("data-target")
  val ariaExpanded = new CustomAttribute[Boolean]("aria-expanded")
  val ariaControls = new CustomAttribute[String]("aria-controls")

  override def render(): ReactElement = {
    a(className := "btn",
      dataToggle := "collapse",
      dataTarget := s"#${props.section}",
      ariaExpanded := props.expanded,
      ariaControls := props.section)(
      props.text
    )
  }
}
