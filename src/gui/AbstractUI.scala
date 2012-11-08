package gui

class AbstractUI(xDim: Int, yDim: Int) {
  private[this] val stage = new Stage((xDim, yDim))
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
  def render(x: Double, y: Double) {
    stage.renderBlock(x,y)
  }
  def space() {
  }
  def view: GameView = stage.view
}