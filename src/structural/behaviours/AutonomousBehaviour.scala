package ScalaWorld.structural.behaviours

import gui.ActorAvatar
import ScalaWorld.structural.behaviours.enum.SleepBehaviour

/**
 * Autonomous behaviour as per each avatar
 * @param name the behavioral name
 */
abstract class AutonomousBehaviour (name: String, classification: BehaviourClassificationEnum.Value, avatar: ActorAvatar) extends Behaviours (name, classification, avatar){

  inhibitions += SleepBehaviour // each autonomous behaviour will have a sleep behaviour
  inhibitions += CollisionBehaviour
  // all autonomous activity is disabled at the start
  myActivity = 0
}
