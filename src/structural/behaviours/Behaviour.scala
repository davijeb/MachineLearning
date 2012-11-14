package ScalaWorld.structural.behaviours

import actors.Actor
import gui.ActorAvatar
import ScalaWorld.enumerations.RequestEnum
import ScalaWorld.actions._
import java.util.{Observer, Observable}
import ScalaWorld.messaging.{WakeOrSleep, MoveRequest, SWRequest}
import javax.swing.text.DefaultEditorKit.SelectWordAction
import ScalaWorld.structural.Animated
import java.util
import ScalaWorld.sensors.Sensor
import collection.mutable.ListBuffer

abstract class Behaviour(name: String, classification: BehaviourClassificationEnum.Value, avatar: ActorAvatar) extends Actor with Animated {

  // start the actor thread
  start()

  val inhibitions:ListBuffer[Behaviour] = new ListBuffer[Behaviour]
  val sensors: List[Sensor]             = List()
  val theirActivity: List[Behaviour]    = List()

  val INHIBITION_THRESHOLD = 50;

  var inhibitionSum = 0;

  var myActivity = 0// until 100 //  needs to have a default of 0

  def move(request: RequestEnum.Value) {
    checkForInhibition

    if (inhibitionSum < INHIBITION_THRESHOLD)
      SWAction.action(new MoveRequest(request,avatar))
  }

  def checkForInhibition { inhibitionSum = 0; inhibitions.foreach(inhibitionSum += _.myActivity) }

  def doCycle() {

  }

  def act

}


