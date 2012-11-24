package ScalaWorld.structural.behaviours

import actors.Actor
import classification.BehaviourClassificationEnum
import gui.ActorAvatar
import ScalaWorld.enumerations.RequestEnum
import ScalaWorld.actions._
import ScalaWorld.messaging.MoveRequest
import ScalaWorld.structural.Animated
import ScalaWorld.sensors.Sensor
import collection.mutable.ListBuffer

abstract class Behaviours(name: String, classification: BehaviourClassificationEnum.Value, avatar: ActorAvatar) extends Actor with Animated {

  // start the actor thread
  start()

  val inhibitions:ListBuffer[Behaviours] = new ListBuffer[Behaviours]
  val sensors: List[Sensor]             = List()
  val theirActivity: List[Behaviours]    = List()

  val myAvatar = avatar

  val INHIBITION_THRESHOLD = 50;

  var inhibitionSum = 0;

  var myActivity = 0// until 100 //  needs to have a default of 0

//  def move(request: RequestEnum.Value) {
//    checkForInhibition
//
//    if (inhibitionSum < INHIBITION_THRESHOLD)
//      SWAction.action(new MoveRequest(request,avatar))
//  }

  def checkForInhibition { inhibitionSum = 0; inhibitions.foreach(inhibitionSum += _.myActivity) }

  def doCycle() {

  }

  def act

}


