package ScalaWorld.messaging

import gui.SWGUI
import ScalaWorld.enumerations.{RequestEnum, OutcomeEnum}
import ScalaWorld.structural.Animated
import ScalaWorld.SWEnvironment
import javax.swing.SwingUtilities

class MoveRequest(act: RequestEnum.Value, obj: Animated) extends SWRequest(act: RequestEnum.Value, obj: Animated) {

  def create(action: RequestEnum.Value, obj: Animated): SWRequest = {
    new MoveRequest(action, obj)
  }
  override def call(): OutcomeEnum.Value = {
    SWGUI.ui.move(act, obj)
  }

}
