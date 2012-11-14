package ScalaWorld.structural.behaviours

import gui.ActorAvatar
import ScalaWorld.structural.behaviours.enum.SleepBehaviour

/**
 * Autonomous behaviour as per each avatar
 * @param name the behavioral name
 */
abstract class AutonomousBehaviour (name: String, classification: BehaviourClassificationEnum.Value, avatar: ActorAvatar) extends Behaviour (name, classification, avatar){

  inhibitions += SleepBehaviour // each autonomous behaviour will have a sleep behaviour

  // all autonomous activity is disabled at the start
  myActivity = 0
}
