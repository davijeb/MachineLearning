package gui

import swing._
import event.{Event, MouseClicked, KeyPressed}
import javax.imageio.ImageIO
import java.io.File
import javax.swing.{SwingUtilities, ImageIcon}
import ScalaWorld.SWEnvironment

import ScalaWorld.enumerations.RequestEnum
import ScalaWorld.actions.{Wakeup, BEEP, SWAction}
import ScalaWorld.messaging.{Timer, MoveRequest}
import ScalaWorld.gui.{AMovementEvent}
import ScalaWorld.structural.behaviours.MoveBehaviour
import ScalaWorld.structural.{RobotFactory, Robot}
import collection.mutable.ListBuffer
import translations.XY


/**
 * Main GUI application
 */
object SWGUI extends SimpleSwingApplication {
  import event.Key._
  import java.awt.{Dimension, Graphics2D, Graphics, Image, Rectangle}
  import java.awt.{Color => AWTColor}

  // Define the width and height of the screen
  val xDim = 800
  val yDim = 600

  // how big are the blocks to be?
  val blockSize   = 10

  val uis = ListBuffer[AbstractUI]();

  val robots = ListBuffer[Robot]()

  // add the robots here
  robots += RobotFactory.get("Avatar 1", new XY(3,0))
  //robots += RobotFactory.get("Avatar 2", new XY(4,0))

  //robots.foreach(uis +=  new AbstractUI(xDim/blockSize, yDim/blockSize, new XY(0,0)))

  val ui = new AbstractUI(xDim/blockSize, yDim/blockSize, new XY(0,0), robots)

  val rand = new scala.util.Random()

  val requests = Seq[RequestEnum.Value](RequestEnum.FORWARD, RequestEnum.RIGHT, RequestEnum.BACK, RequestEnum.LEFT)

  def top = new MainFrame {
    title = "World"
    contents = mainPanel

    Timer(500) {
      robots.foreach(_.doCycle())
    }
  }

  def mainPanel = new Panel {
    preferredSize = new Dimension(xDim, yDim)
    focusable = true

    // Let the panel listen to key strokes and mouse events
    listenTo(keys)
    listenTo(mouse.clicks)
    listenTo(ui.stage)

    // add a movement event handler
    reactions += {
      case KeyPressed(_, key, _, _) =>
        onKeyPress(key)
        repaint()
    }

    // add a mouse click (create boundaries) handler
    reactions += {
      case e: MouseClicked =>
        ui.render(e.point.getX.toInt,e.point.getY.toInt)
        repaint()
    }
    reactions += {
      case AMovementEvent => repaint();  val x = 0
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
    case S     => ui.fireSleepSensor()
    case _ =>
  }

  def onPaint(g: Graphics2D) {

    val view = ui.view

    def buildRect(pos: (Int, Int)): Rectangle =
      new Rectangle(pos._1 * blockSize,(view.gridSize._2 - pos._2 - 1) * blockSize, blockSize, blockSize)

    /**
     * Loop from 0 -> xDim/blockSize, 0 -> yDim/blockSize to create an NxM Matrix
     * containing n*m blocks
     */
    def drawEmptyGrid {
      g setColor new AWTColor(200, 200, 99)
      for {
        x <- 0 to view.gridSize._1 - 1
        y <- 0 to view.gridSize._2 - 1
      } g draw buildRect((x, y))
    }

    def drawBlocks {
      view.blocks.values foreach { b =>  {
        if(b.kind.render) {
          g setColor b.kind.colour
          g fill buildRect(b.pos)
        }
      } }
    }

    def drawCurrent {
      g setColor new AWTColor(210, 255, 255)
      view.avatars foreach { a => g fill buildRect(a.position) }
    }

    drawEmptyGrid
    drawBlocks
    drawCurrent

  }
}