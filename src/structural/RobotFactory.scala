package ScalaWorld.structural

import behaviours.classification.BehaviourClassificationEnum
import behaviours.specific.{BehaviourAvoidance, BehaviourCollision, BehaviourSleep, BehaviourMove}
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
    val robot  = new Robot(avatar)

    robot + (BehaviourClassificationEnum.SLEEP,new BehaviourSleep())
    robot + (BehaviourClassificationEnum.SLEEP,new BehaviourMove())
    robot + (BehaviourClassificationEnum.SLEEP,new BehaviourCollision())
    robot + (BehaviourClassificationEnum.SLEEP,new BehaviourAvoidance())

    robot
  }

}
