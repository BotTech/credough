import slinky.core.StatelessComponentWrapper
import slinky.core.facade.ReactElement

import scala.scalajs.js

object HelloApp extends StatelessComponentWrapper {

  type Props = Unit

  class Def(jsProps: js.Object) extends Definition(jsProps) {

    def render: ReactElement = {
      throw new Exception("BOO")
    }
  }

}
