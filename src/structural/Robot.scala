package ScalaWorld.structural

import behaviours.Behaviour
import collection.mutable.ListBuffer
import gui.ActorAvatar

class Robot(avatar: ActorAvatar) {

  val actorAvatar = avatar

  val behaviours = new ListBuffer[Behaviour];

  def doCycle() {
   behaviours.foreach(_.doCycle())
  }

  /**
   * Fluent-interface to add a behaviour to the robot and
   * return this
   * @param b the behaviour
   * @return me
   */
  def +(b: Behaviour): Robot = {
    behaviours += b
    this
  }

}
