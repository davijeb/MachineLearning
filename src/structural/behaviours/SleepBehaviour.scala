package ScalaWorld.structural.behaviours.enum

import gui.ActorAvatar
import ScalaWorld.structural.behaviours.{Behaviour, BehaviourClassificationEnum, GodBehaviour}
import ScalaWorld.messaging.WakeOrSleep

object SleepBehaviour extends GodBehaviour("",null, null) {
  // all god like behaviour is active at the start
  myActivity = 100

  /**
   * Able to receive inputs from external sources
   */
  def act() {
    while(true) {
      receive {
        case WakeOrSleep => myActivity = 0;
      }
    }
  }
}
