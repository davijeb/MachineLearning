package ScalaWorld.messaging

import gui.{ActorAvatar, SWGUI}
import ScalaWorld.enumerations.{RequestEnum, OutcomeEnum}
import ScalaWorld.structural.Animated
import ScalaWorld.SWEnvironment
import javax.swing.SwingUtilities

class MoveRequest(act: RequestEnum.Value, avatar: ActorAvatar) extends SWRequest(act: RequestEnum.Value, avatar: ActorAvatar) {

  def create(action: RequestEnum.Value, avatar: ActorAvatar): SWRequest = {
    new MoveRequest(action, avatar)
  }
  override def call(): OutcomeEnum.Value = {
    SWGUI.ui.move(act, avatar)
  }

}
