package ScalaWorld.structural

import behaviours.Behaviours
import collection.mutable.ListBuffer
import gui.ActorAvatar
import actors.Actor
import ScalaWorld.messaging.WakeOrSleep
import ScalaWorld.sensors.SensorRefresh

class Robot(avatar: ActorAvatar) {

  val actorAvatar = avatar
  val behaviours = new ListBuffer[Behaviours]

  def doCycle() {
   behaviours.foreach(_.doCycle())
  }

  /**
   * Fluent-interface to add a behaviour to the robot and
   * return this
   * @param b the behaviour
   * @return me
   */
  def +(b: Behaviours): Robot = {
    behaviours += b
    this
  }
}
