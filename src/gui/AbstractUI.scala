package gui

import ScalaWorld.enumerations.{RequestEnum, OutcomeEnum}
import ScalaWorld.structural.Animated

/**
 * Modified...
 * @param xDim
 * @param yDim
 */
class AbstractUI(xDim: Int, yDim: Int) {
  val stage = new Stage((xDim, yDim))
  def left() {
    stage.moveLeft()
  }
  def right() {
    stage.moveRight()
  }
  def up() {
    stage.moveUp()
  }
  def down() {
    stage.moveDown()
  }
  def render(x: Int, y: Int) {
    stage.renderBlock(x,y)
  }
  def move(act: RequestEnum.Value, obj: Animated): OutcomeEnum.Value = {
    stage.move(act, obj)
  }
  def space() {
  }
  def view: GameView = stage.view
}