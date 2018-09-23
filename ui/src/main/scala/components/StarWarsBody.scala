package components

import slinky.core.annotations.react
import slinky.core.facade.ReactElement
import slinky.core.{CustomAttribute, StatelessComponent}
import slinky.web.html._

@react class StarWarsBody extends StatelessComponent {

  case class Props(sangriaImageURL: String, graphIQLURL: String)

  val dataToggle = new CustomAttribute[String]("data-toggle")
  val dataTarget = new CustomAttribute[String]("data-target")
  val ariaExpanded = new CustomAttribute[Boolean]("aria-expanded")
  val ariaControls = new CustomAttribute[String]("aria-controls")

  override def render(): ReactElement = {
    header(
      div(className := "container")(
        div(className := "row")(
          div(className := "col-sm-7 blog-main")(
            section(
              h2("GraphQL Query"),
              div(id := "queryEditor")
            ),
            QueryEditorButton("Veriables", "variablesSection", false)
          )
        )
      )
    )
  }
}
