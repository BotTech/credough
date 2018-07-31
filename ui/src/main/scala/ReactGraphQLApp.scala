import com.apollographql.scalajs.ApolloBoostClient
import com.apollographql.scalajs.react.ApolloProvider
import org.scalajs.dom
import org.scalajs.dom.Element
import slinky.core.Component
import slinky.core.facade.ReactInstance
import slinky.web.ReactDOM

trait ReactGraphQLApp {

  protected def rootElementID: String = "root"

  protected def uri: String

  protected def app: Component

  protected def renderApp(): ReactInstance = {
    val container = root().getOrElse(createRoot())
    // TODO: Explore other client options
    val client = ApolloBoostClient(uri)
    // FIXME: Is it ok to render the component here and then again after being wrapped?
    val wrapped = ApolloProvider(client)(app.render())
    ReactDOM.render(wrapped, container)
  }

  private def root(): Option[Element] = {
    Option(dom.document.getElementById(rootElementID))
  }

  private def createRoot(): Element = {
    // TODO: Is there a better way to create dom elements?
    val elem = dom.document.createElement("div")
    elem.id = rootElementID
    val _ = dom.document.body.appendChild(elem)
    elem
  }
}
