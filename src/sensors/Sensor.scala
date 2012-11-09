package ScalaWorld.sensors

import collection.mutable
import data.MovementSensorData
import sun.applet.resources.MsgAppletViewer
import collection.script.{Message, Remove}

/**
 * Created with IntelliJ IDEA.
 * User: martin
 * Date: 06/11/12
 * Time: 15:51
 * To change this template use File | Settings | File Templates.
 */
class Sensor extends mutable.Subscriber[Message[MovementSensorData], mutable.ObservableSet[MovementSensorData]] {

  def notify(pub: mutable.ObservableSet[MovementSensorData], event: Message[MovementSensorData]): Unit = {
    println(event)
  }

}
