package ScalaWorld.structural.behaviours.specific

import actors.Actor
import gui.ActorAvatar
import ScalaWorld.enumerations.Orientation
import ScalaWorld.structural.behaviours.classification.BehaviourClassificationEnum

class BehaviourAvoidance extends BehaviourAutonomous with Actor {

  var myActivity:Int = 0

  start()
  /**
   * Able to receive inputs from external sources
   */
  def act() {
    loop {
      react {
        case (av: ActorAvatar) => {
          av.orientation = Orientation.East;
        }
      }
    }
  }
}