package ScalaWorld.structural.behaviours.specific

import ScalaWorld.enumerations.RequestEnum
import ScalaWorld.actions.SWAction
import ScalaWorld.messaging.MoveRequest
import ScalaWorld.structural.behaviours.classification.BehaviourClassificationEnum

class BehaviourMove extends BehaviourAutonomous {

  var myActivity = 0

  val defaultMovement = RequestEnum.FORWARD

//  override def doCycle() {
//    super.checkForInhibition
//    if (inhibitionSum < INHIBITION_THRESHOLD)
//      SWAction.action(new MoveRequest(defaultMovement, avatar))
//  }
}
