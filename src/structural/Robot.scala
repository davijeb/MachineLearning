package ScalaWorld.structural

import behaviours.classification.BehaviourClassificationEnum
import behaviours.{Behaviour, RobotBehaviours}
import collection.mutable.ListBuffer
import gui.ActorAvatar
import actors.Actor
import ScalaWorld.messaging.WakeOrSleep
import ScalaWorld.sensors.SensorRefresh

class Robot(avatar: ActorAvatar) {

  val robotAvatar = avatar

  val behaviours = new RobotBehaviours(this)

  def doCycle() {
   behaviours.doCycle
  }

  /**
   * Fluent-interface to add a behaviour to the robot and
   * return this
   * @param b the behaviour
   * @return me
   */
  def +(bc: BehaviourClassificationEnum.Value, b: Behaviour): Robot = {
    behaviours + (bc,b)
    this
  }
}
