package ScalaWorld.structural.behaviours

import actors.Actor
import classification.BehaviourClassificationEnum
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

abstract class Behaviour {

    var myActivity:Int

}

