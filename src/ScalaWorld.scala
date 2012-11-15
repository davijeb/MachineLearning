package ScalaWorld

import enumerations.LandmarkEnumerations
import messaging.{RequestDispatcher, RequestExecutor}
import scala.swing._
import scala.swing.event._
import enumerations.LandmarkEnumerations._
import structural.SWActor
import enumerations.RequestEnum
import collection.immutable.HashMap

object ScalaWorld {

  class Sense (name: String)

  class Motivation (name: String)

  class Landmark ()

  class GlobalMap ()

  class ActionLandmark (action: RequestEnum.Value,landmark: LandmarkEnumerations.Value)

  class LocalMap ()


}
