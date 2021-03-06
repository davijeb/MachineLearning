package gui

import io.Source
import sys.process._
import java.io.{PrintWriter, File}
import ScalaWorld.enumerations.{RequestEnum, OutcomeEnum, Orientation}
import ScalaWorld.structural.behaviours.classification.BehaviourClassificationEnum
import ScalaWorld.structural.behaviours.specific.{BehaviourMove, BehaviourCollision}
import ScalaWorld.structural.behaviours.{Behaviour}
import ScalaWorld.structural.{Robot, Animated}
import translations.{XY, CoordTranslater}
import swing.Publisher
import ScalaWorld.gui.{AMovementEvent}
import collection.script._
import collection.mutable.{ListBuffer, Seq}
import collection.mutable
import ScalaWorld.structural.Robot
import ScalaWorld.sensors.{SensorArray, SensorRefresh}
import scala.collection.mutable.Map

class Stage(size: (Int, Int), xy: XY, robots: List[Robot]) extends Publisher {

  private[this] val blocks  = Map[XY,Block]()

  // Flood the grid with empty blocks initially
  for {
    x <- 0 to size._1 - 1
    y <- 0 to size._2 - 1
  } blocks += (new XY(x, y) -> new Block(new XY(x,y), EmptyKind))

  private[this] val COORDINATE_FILE = "c:/Users/Public/coordinates.txt"

  // Define a game view to hold onto the blocks, stage size and the
  def view: GameView = GameView(blocks, size, robots)

  if (Source.fromFile(COORDINATE_FILE).mkString != null)
    loadFromFile(Option(Source.fromFile(COORDINATE_FILE).mkString))

  /**
   * Fire the collisions sensors at the start otherwise
   * the robots may just wander into a fixed block
   */
 // avatars.foreach( CollisionBehaviour ! (_))

  def loadFromFile(t: Option[String]) {
    t match {
      case Some(t) => drawBlocks(Option(t).get.split("\\),"))
      case None => println("No previously stored coordinates to load.")
    }
  }

  // this could be written as

  /**
   * def drawBlocks(coords: Array[String]) {
      drawBlock(coords) then the other method would be

   def drawBlock(s: _*)    {
  }

   This notation tells the compiler to pass each element of arr as its own argument to echo, rather than all of it as a single argument.
  */
  def drawBlocks(coords: Array[String]) {
    if (coords.size > 1)
      coords.foreach(t => drawBlock(t.substring(1)))
  }

  def drawBlock(s: String)    {
    val tuple =  s.split(",")
    val t1 = tuple(0).toInt
    val t2 = tuple(1).toInt

    renderBlock(t1,t2)
  }

  // 4-axis functions
  //def moveUp()    = moveBy(0, 1)
  //def moveRight() = moveBy(1, 0)
  //def moveDown()  = moveBy(0, -1)
  //def moveLeft()  = moveBy(-1, 0)

  private[this] def moveBy(delta: (Int, Int), robot: Robot): OutcomeEnum.Value = {
    move(_.moveBy(delta), robot)
  }

  private[this] def moveTo(delta: (Int, Int), robot: Robot): OutcomeEnum.Value = {
    move(_.moveTo(delta), robot)
  }

  private[this] def move(mover: ActorAvatar => ActorAvatar, robot: Robot): OutcomeEnum.Value = {

    // set the avatar next position
    var moved = mover(robot.robotAvatar)

    // get the new x and x positions to check for boundary break
    val px = moved.position._1
    val py = moved.position._2

    // optimistically think the move will be okay
    var ok = true

    robot.robotAvatar.immutableMove(new XY(px, py))

    ok match {
      case(true)  => OutcomeEnum.SUCCESS
      case(false) => OutcomeEnum.FAILURE
    }
  }

  private[this] def edgeCheck(px: Int, py: Int): Boolean = {
    if((px >= 0 && px < size._1) || !(py >= 0 && py < size._2)) {
      false
    }
    true
  }

  def renderBlockFromCoordinates(point: (Int, Int)) {
    val px = (math.floor(  point._1 / SWGUI.blockSize)).toInt
    val py = (size._2 - 1 - math.floor(point._2 / SWGUI.blockSize)).toInt

    renderBlock((px,py))
  }

  /**
   * Render a piece at a set location. This will be a structural element
   * that will define boundaries such as walls, doors and windows
   *
   * @param point the x,y coordinates
   */
  def renderBlock(point: (Int, Int)) {

    val xy = new XY(point._1, point._2)

    blocks(xy) =  new Block(xy, FixedKind)

    var text = Source.fromFile(COORDINATE_FILE).mkString
    val writer = new PrintWriter(new File(COORDINATE_FILE))

    writer.append(text.+(xy)+",")
    writer.close()

  }

  /**
   * Move the current avatar - wrong as there needs to be more thn one of them
   * @return
   */
  def move(direction: RequestEnum.Value, robot: Robot): OutcomeEnum.Value = {

    val avatar = robot.robotAvatar
    val orientation = avatar.orientation;

    // based on the orientation and direction we can work out the next xy coordinates
    // using the CoordTranslater
    val xy = CoordTranslater.getTransformation(avatar.position,avatar.orientation,direction)

    // publish the move event for the reactor to fire
    publish(AMovementEvent)

    // call to move the actor to a new block
    val outcome = moveTo(xy, robot)

    //BehaviourCollisio ! (avatar)

    outcome
  }

  def locationIdent(avatar: ActorAvatar): (Boolean, Boolean, Boolean, Boolean) = {

    val p = avatar.position
    val o = avatar.orientation

    (
       blocks.getOrElse(CoordTranslater.getTransformation(p,o,RequestEnum.FORWARD),new Block((-1,-1),FixedKind)).kind == FixedKind,
       blocks.getOrElse(CoordTranslater.getTransformation(p,o,RequestEnum.RIGHT),  new Block((-1,-1),FixedKind)).kind == FixedKind,
       blocks.getOrElse(CoordTranslater.getTransformation(p,o,RequestEnum.BACK),   new Block((-1,-1),FixedKind)).kind == FixedKind,
       blocks.getOrElse(CoordTranslater.getTransformation(p,o,RequestEnum.LEFT),   new Block((-1,-1),FixedKind)).kind == FixedKind
     )
  }
}