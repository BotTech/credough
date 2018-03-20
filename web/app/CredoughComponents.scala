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

import com.softwaremill.macwire._
import controllers.HelloController
import play.api.ApplicationLoader.Context
import play.api.BuiltInComponentsFromContext
import play.filters.HttpFiltersComponents
import router.Routes

class CredoughComponents(context: Context)
  extends BuiltInComponentsFromContext(context)
    with HttpFiltersComponents {

  lazy val helloController: HelloController = wire[HelloController]

  lazy val prefix: String = "/"

  lazy val router: Routes = wire[Routes]
}
