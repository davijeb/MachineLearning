package ScalaWorld.structural.behaviours.specific

import actors.Actor
import gui.{SWGUI, ActorAvatar}
import ScalaWorld.enumerations.LandmarkEnumerations
import ScalaWorld.sensors.ProximityClassifier
import ScalaWorld.structural.behaviours.classification.BehaviourClassificationEnum

class BehaviourCollision extends BehaviourAutonomous {

  var myActivity:Int = 100

//  start()
//  /**
//   * Able to receive inputs from external sources
//   */
//  def act() {
//    loop {
//      react {
//        case (av: ActorAvatar) => {
//
//          val landmark = ProximityClassifier.classify(SWGUI.ui.stage.locationIdent(av))
//
//          if (landmark.lndmarkEnum == LandmarkEnumerations.BLINDALLEY_F) {
//            myActivity = 100
//          }
//        }
//      }
//    }
//  }
}