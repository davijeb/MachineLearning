package ScalaWorld.messaging

import gui.{ActorAvatar, SWGUI}
import ScalaWorld.enumerations.{RequestEnum, OutcomeEnum}
import ScalaWorld.structural.{Robot, Animated}
import ScalaWorld.SWEnvironment
import javax.swing.SwingUtilities

class MoveRequest(act: RequestEnum.Value, robot: Robot) extends SWRequest(act: RequestEnum.Value, robot: Robot) {

  def create(action: RequestEnum.Value, robot: Robot): SWRequest = {
    new MoveRequest(action, robot)
  }
  override def call(): OutcomeEnum.Value = {
    SWGUI.ui.move(act, robot)
  }

}
