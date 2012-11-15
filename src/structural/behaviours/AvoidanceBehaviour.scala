package ScalaWorld.structural.behaviours

import actors.Actor
import gui.{SWGUI, ActorAvatar}
import ScalaWorld.enumerations.{Orientation, LandmarkEnumerations}
import ScalaWorld.sensors.ProximityClassifier

class AvoidanceBehaviour extends AutonomousBehaviour("",null, null) with Actor {

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