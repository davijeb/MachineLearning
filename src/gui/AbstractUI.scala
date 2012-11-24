package gui

import ScalaWorld.enumerations.{RequestEnum, OutcomeEnum}
import ScalaWorld.structural.{Robot, Animated}
import translations.XY
import collection.mutable.ListBuffer
import ScalaWorld.messaging.WakeOrSleep
import ScalaWorld.structural.behaviours.Behaviour
import com.sun.corba.se.spi.protocol.ForwardException

/**
 * Modified...
 * @param xDim
 * @param yDim
 */
class AbstractUI(xDim: Int, yDim: Int, xy: XY, robots: ListBuffer[Robot]) {
  val stage = new Stage((xDim, yDim), xy, robots.toList)
  def left() {
    //stage.moveLeft()
  }
  def right() {
    //stage.moveRight()
  }
  def up() {
    //stage.moveUp()
  }
  def down() {
    //stage.moveDown()
  }
  def render(x: Int, y: Int) {
    stage.renderBlockFromCoordinates(x,y)
  }
  def move(act: RequestEnum.Value, robot: Robot): OutcomeEnum.Value = {
    stage.move(act, robot)
  }
  def fireSleepSensor() {
   // robots.foreach(_.behaviours.foreach(_.inhibitions.foreach(_ ! WakeOrSleep)))
  }

  def view: GameView = stage.view
}