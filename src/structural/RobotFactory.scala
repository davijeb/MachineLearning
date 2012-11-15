package ScalaWorld.structural

import behaviours.{MoveBehaviour, Behaviour}
import gui.{AnimKind, ActorAvatar}
import ScalaWorld.enumerations.Orientation
import translations.XY
import scala.collection.mutable.Map

/**
 * Factory to create various different types of robots
 */
object RobotFactory {

  val cache = Map[ActorAvatar, Robot]()

  def get(name: String, xy: XY): Robot = {
    val avatar = new ActorAvatar(name, xy, AnimKind, Orientation.North)
    val robot  = new Robot(avatar) + new MoveBehaviour("Movement behaviour", avatar)

    // we need to ensure the avatar knows about the controlling robot
    avatar.setRobot(robot)

    cache += (avatar -> robot)

    robot
  }

}
