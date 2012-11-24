package ScalaWorld.structural.behaviours

import classification.BehaviourClassificationEnum
import ScalaWorld.structural.Robot
import scala.collection.mutable.Map

class RobotBehaviours(robot: Robot) {

  var inhibitionSum = 0;

  val inhibitors = Map[BehaviourClassificationEnum.Value, Behaviour]()
  val behaviours = Map[BehaviourClassificationEnum.Value, Behaviour]()

  //def checkForInhibition { inhibitionSum = 0; inhibitors.foreach(inhibitionSum += _.myActivity) }

  /**
   * Add a behaviour
   * @param bc behaviour class
   * @param b the behaviour
   * @return the robot behaviour
   */
  def ++(bc: BehaviourClassificationEnum.Value, b: Behaviour): RobotBehaviours = {
    inhibitors + (bc -> b)
    this
  }

  /**
   * Add an inhibitor
   * @param bc behaviour class
   * @param b the inhibitor
   * @return the robot behaviour
   */
  def +(bc: BehaviourClassificationEnum.Value, b: Behaviour): RobotBehaviours = {
    behaviours + (bc -> b)
    this
  }

  def doCycle {

  }

}
