package ScalaWorld.structural.behaviours

import gui.ActorAvatar
import java.util
import ScalaWorld.enumerations.RequestEnum
import ScalaWorld.actions.SWAction
import ScalaWorld.messaging.{WakeOrSleep, SWRequest, MoveRequest}

class MoveBehaviour (name: String, avatar: ActorAvatar) extends AutonomousBehaviour (name, BehaviourClassificationEnum.MOVE, avatar){

  val defaultMovement = RequestEnum.FORWARD

  override def doCycle() {
    super.checkForInhibition
    if (inhibitionSum < INHIBITION_THRESHOLD)
      SWAction.action(new MoveRequest(defaultMovement, avatar))
  }

  override def act() {

  }
}
