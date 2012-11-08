package ScalaWorld.structural

import actors.Actor
import ScalaWorld.enumerations.RequestEnum
import ScalaWorld.actions._
import java.util.{Observer, Observable}
import ScalaWorld.messaging.{MoveRequest, SWRequest}
import javax.swing.text.DefaultEditorKit.SelectWordAction

class SWActor extends Animated {

  def move() {
    SWAction.action(new MoveRequest(RequestEnum.FORWARD, this))
  }
}
