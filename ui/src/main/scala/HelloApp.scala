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

import slinky.core.{StatelessComponent, StatelessComponentWrapper}
import slinky.core.annotations.react
import slinky.core.facade.ReactElement

import scala.scalajs.js

//object HelloApp extends StatelessComponentWrapper {
//
//  type Props = Unit
//
//  class Def(jsProps: js.Object) extends Definition(jsProps) {
//
//    // TODO: Add GraphQL thingy here
//    override def render(): ReactElement = {
//      div(className := "App")(
//        header(className := "App-header")(
//          h1(className := "App-title")("Welcome to React (with Scala.js!)")
//        ),
//        p(className := "App-intro")(
//          "To get started, edit ", code("HelloApp.scala"), " and save to reload."
//        )
//      )
//    }
//  }
//
//}

object HelloApp extends StatelessComponentWrapper {

  type Props = Unit

  class Def(jsProps: js.Object) extends Definition(jsProps) {
    def render: ReactElement = {
      throw new Exception("BOO")
    }
  }
}
