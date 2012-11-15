package ScalaWorld.sensors

import actors.Actor
import gui.{ActorAvatar, SWGUI}
import ScalaWorld.structural.RobotFactory

/**
 * Created with IntelliJ IDEA.
 * User: martin
 * Date: 14/11/12
 * Time: 13:24
 * To change this template use File | Settings | File Templates.
 */
object SensorArray extends Actor {

  start()

  def act() {
    loop {
      react {
        case (avatar: ActorAvatar) => {
          RobotFactory.cache(avatar).behaviours.foreach(_.inhibitions.foreach(_.sensors))
          println("Sensor array wants to do something with " + avatar.ident)
        }
      }
    }
  }

}
