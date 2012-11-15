package ScalaWorld.structural.behaviours

import actors.Actor
import gui.{SWGUI, ActorAvatar}
import ScalaWorld.structural.RobotFactory
import ScalaWorld.messaging.WakeOrSleep
import ScalaWorld.enumerations.LandmarkEnumerations
import ScalaWorld.sensors.ProximityClassifier

object CollisionBehaviour extends AutonomousBehaviour("",null, null) with Actor {

  start()
  /**
   * Able to receive inputs from external sources
   */
  def act() {
    loop {
      react {
        case (av: ActorAvatar) => {

          val landmark = ProximityClassifier.classify(SWGUI.ui.stage.locationIdent(av))

          if (landmark.lndmarkEnum == LandmarkEnumerations.BLINDALLEY_F) {
            myActivity = 100
          }
        }
      }
    }
  }
}