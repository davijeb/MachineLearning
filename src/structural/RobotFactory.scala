package ScalaWorld.structural

import behaviours.{MoveBehaviour, Behaviour}
import gui.{AnimKind, ActorAvatar}
import ScalaWorld.enumerations.Orientation
import translations.XY

/**
 * Factory to create various different types of robots
 */
object RobotFactory {

  def get(name: String, xy: XY): Robot = {
    val avatar = new ActorAvatar(name, xy, AnimKind, Orientation.North)
    new Robot(avatar)+new MoveBehaviour("Movement behaviour", avatar)
  }

}
