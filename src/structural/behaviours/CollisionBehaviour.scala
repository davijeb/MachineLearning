package ScalaWorld.structural.behaviours

import actors.Actor
import gui.{SWGUI, ActorAvatar}
import ScalaWorld.structural.RobotFactory
import ScalaWorld.messaging.WakeOrSleep
import ScalaWorld.enumerations.LandmarkEnumerations
import ScalaWorld.sensors.ProximityClassifier

object CollisionBehaviour extends AutonomousBehaviour("",null, null) with Actor {

  //val landmark = SWGUI.ui.stage.locationIdent(av)
  //println("Landmark: " + landmark)

  start
  /**
   * Able to receive inputs from external sources
   */
  def act() {
    loop {
      react {
        case (av: ActorAvatar) => {
          val landscape = SWGUI.ui.stage.locationIdent(av)
          val landmark = ProximityClassifier.classify(landscape)
          println(av.ident + " - " + landmark)
        }
    }
    }
  }
}