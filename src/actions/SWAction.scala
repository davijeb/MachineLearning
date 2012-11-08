package ScalaWorld.actions

import ScalaWorld.messaging._
import ScalaWorld.enumerations._
import collection.immutable.HashMap
import ScalaWorld._

class SWAction(name: String, currentAction: RequestEnum.Value, currentOutcome: OutcomeEnum.Value, lastOutcome: OutcomeEnum.Value) extends Enumeration {
}

object SWAction     {
  def action(request: SWRequest)  {
    RequestDispatcher.offer(request)
  }
}
