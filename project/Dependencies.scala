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

import org.portablescala.sbtplatformdeps.PlatformDepsPlugin.autoImport._
import sbt._

trait Dependencies {

  object Versions {

    // TODO: What about dependency updates plugin for npm modules?
    val aceCodeEditor = "1.2.3"
    val apollo = "1.9.2"
    val apolloBoost = "0.1.16"
    val apolloClient = "2.4.2"
    val bloomer = "0.6.3"
    val bulma = "0.7.1"
    // TODO: What about react-fontAwesome
    val fontAwesome = "4.7.0"
    // FIXME: apollo-boost needs to be updated to graphQL 14
    val graphQL = "0.13.2"
    val graphQLTag = "2.9.2"
    val macroParadise = "3.0.0-M11"
    val macwire = "2.3.1"
    val react = "16.5.2"
    val reactApollo = "2.1.11"
    val reactApolloScalaJS = "0.4.0"
    val reactProxy = "1.1.8"
    val sangria = "1.4.1"
    val sangriaPlayJson = "1.0.4"
    val scala = "2.12.6"
    val slinky = "0.4.3"
  }

  object ScalaJS {

    // TODO: Do we need core? Is it not a transitive dependency?
    val reactApolloCore = Def.setting("com.apollographql" %%% "apollo-scalajs-core" % Versions.reactApolloScalaJS)
    val reactApolloScalaJS = Def.setting("com.apollographql" %%% "apollo-scalajs-react" % Versions.reactApolloScalaJS)
    val slinkyHot = Def.setting("me.shadaj" %%% "slinky-hot" % Versions.slinky)
    val slinkyWeb = Def.setting("me.shadaj" %%% "slinky-web" % Versions.slinky)

    val dependencies = Def.setting {
      Seq(
        reactApolloCore.value,
        reactApolloScalaJS.value,
        slinkyHot.value,
        slinkyWeb.value
      )
    }
  }

  val macroParadise = "org.scalameta" % "paradise" % Versions.macroParadise cross CrossVersion.full
  // TODO: Do we really need this?
  val macwire = "com.softwaremill.macwire" %% "macros" % Versions.macwire % Provided
  val sangria = "org.sangria-graphql" %% "sangria" % Versions.sangria
  val sangriaPlayJson = "org.sangria-graphql" %% "sangria-play-json" % Versions.sangriaPlayJson

  // TODO: Move these to WebDependencies
  object Npm {

    sealed trait NpmDependency {
      def name: String
      def version: String
    }

    object NpmDependency {
      implicit def toTuple(npmDependency: NpmDependency): (String, String) = npmDependency.name -> npmDependency.version
    }

    case class CommonJSModule(name: String, version: String) extends NpmDependency
    case class Assets(name: String, version: String, assets: File => PathFinder = _.allPaths) extends NpmDependency

    val aceCodeEditor = CommonJSModule("ace-code-editor", Versions.aceCodeEditor)
    val apollo = CommonJSModule("apollo", Versions.apollo)
    val apolloBoost = CommonJSModule("apollo-boost", Versions.apolloBoost)
    val apolloClient = CommonJSModule("apollo-client", Versions.apolloClient)
    val bloomer = CommonJSModule("bloomer", Versions.bloomer)
    val bulma = CommonJSModule("bulma", Versions.bulma)
    val graphQL = CommonJSModule("graphql", Versions.graphQL)
    val graphQLTag = CommonJSModule("graphql-tag", Versions.graphQLTag)
    val react = CommonJSModule("react", Versions.react)
    val reactApollo = CommonJSModule("react-apollo", Versions.reactApollo)
    val reactDom = CommonJSModule("react-dom", Versions.react)
    val reactProxy = CommonJSModule("react-proxy", Versions.reactProxy)

    // TODO: These should be in the GraphQL plugin.
    val apolloClientCommonJSModules = Seq(
      apolloBoost,
      apolloClient,
      reactApollo,
      graphQL,
      graphQLTag
    )

    val commonJSModules = Seq(
      aceCodeEditor,
      bloomer,
      bulma,
      react,
      reactDom,
      reactProxy
    )

    val fontAwesome = Assets("font-awesome", Versions.fontAwesome)

    val assets = Seq(
      fontAwesome
    )
  }

  val npm: Npm.type = Npm
}

object Dependencies extends Dependencies
