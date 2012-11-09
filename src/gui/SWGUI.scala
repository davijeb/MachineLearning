package gui

import swing._
import event.{MouseClicked, KeyPressed}

/**
 * Main GUI application
 */
object SWGUI extends SimpleSwingApplication{
  import event.Key._
  import java.awt.{Dimension, Graphics2D, Graphics, Image, Rectangle}
  import java.awt.{Color => AWTColor}

  // Define the width and height of the screen
  val xDim = 800
  val yDim = 600

  // how big are the blocks to be?
  val blockSize   = 20

  val ui = new AbstractUI(xDim/blockSize, yDim/blockSize)

  def top = new MainFrame {
    title = "World"
    contents = mainPanel
  }

  def mainPanel = new Panel {
    preferredSize = new Dimension(xDim, yDim)
    focusable = true

    // Let the panel listen to key strokes and mouse events
    listenTo(keys)
    listenTo(mouse.clicks)

    // add a movement event handler
    reactions += {
      case KeyPressed(_, key, _, _) =>
        onKeyPress(key)
        repaint
    }

    // add a mouse click (create boundaries) handler
    reactions += {
      case e: MouseClicked =>
        ui.render(e.point.getX,e.point.getY)
        repaint
    }

    override def paint(g: Graphics2D) {
      g setColor new AWTColor(48, 99, 99)
      g fillRect (0, 0, size.width, size.height)
      onPaint(g)
    }
  }

  def onKeyPress(keyCode: Value) = keyCode match {
    case Left  => ui.left()
    case Right => ui.right()
    case Up    => ui.up()
    case Down  => ui.down()
    case Space => ui.space()
    case _ =>
  }

  def onPaint(g: Graphics2D) {

    val view = ui.view

    def buildRect(pos: (Int, Int)): Rectangle =
      new Rectangle(
        pos._1 * blockSize,
        (view.gridSize._2 - pos._2 - 1) * blockSize,
        blockSize,
        blockSize)

    /**
     * Loop from 0 -> xDim/blockSize, 0 -> yDim/blockSize to create an NxM Matrix
     * containing n*m blocks
     */
    def drawEmptyGrid {
      g setColor new AWTColor(200, 200, 99)
      for {
        x <- 0 to view.gridSize._1 - 1
        y <- 0 to view.gridSize._2 - 1
        pos = (x, y)
      } g draw buildRect(pos)
    }

    def drawBlocks {
      g setColor new AWTColor(200, 99, 99)
      view.blocks foreach { b => g fill buildRect(b.pos) }
    }

    def drawCurrent {
      g setColor new AWTColor(210, 255, 255)
      view.current foreach { b => g fill buildRect(b.pos) }
    }

    drawEmptyGrid
    drawBlocks
    drawCurrent
  }
}
