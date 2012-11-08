package ScalaWorld.messaging

import ScalaWorld.enumerations.{RequestEnum, OutcomeEnum}
import ScalaWorld.structural.Animated
import ScalaWorld.SWEnvironment

class MoveRequest(act: RequestEnum.Value, obj: Animated) extends SWRequest(act: RequestEnum.Value, obj: Animated) {

  def create(action: RequestEnum.Value, obj: Animated): SWRequest = {
    new MoveRequest(action, obj)
  }

  override def call(): OutcomeEnum.Value = {
    SWEnvironment.move(act, obj)
  }

}
