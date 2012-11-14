package gui

import io.Source
import sys.process._
import java.io.{PrintWriter, File}
import dbc.statement.JoinType.Outer.Full
import ScalaWorld.enumerations.{RequestEnum, OutcomeEnum, Orientation}
import ScalaWorld.structural.behaviours.{MoveBehaviour, BehaviourClassificationEnum, Behaviour}
import ScalaWorld.structural.{Robot, Animated}
import translations.{XY, CoordTranslater}
import swing.Publisher
import ScalaWorld.gui.{AMovementEvent}
import collection.script._
import collection.mutable.{ListBuffer, Seq}
import collection.mutable
import ScalaWorld.sensors.Sensor

class Stage(size: (Int, Int), xy: XY, robots: List[Robot]) extends Publisher {

  private[this] val COORDINATE_FILE = "c:/Users/Public/coordinates.txt"

  // Start at 0,0 (bottom left)
  private[this] def dropOffPos = xy

  private[this] val blocks  = ListBuffer[Block]()
  private[this] val avatars = ListBuffer[ActorAvatar]()

  avatars += robots(0).actorAvatar
  avatars += robots(1).actorAvatar

  // Define a game view to hold onto the blocks, stage size and the
  def view: GameView = GameView(blocks.toList, size, avatars.toList)

  if (Source.fromFile(COORDINATE_FILE).mkString != null)
    loadFromFile(Option(Source.fromFile(COORDINATE_FILE).mkString))

  def loadFromFile(t: Option[String]) {
    t match {
      case Some(t) => drawBlocks(Option(t).get.split("\\),"))
      case None => println("No previously stored coordinates to load.")
    }
  }

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

  private[this] def moveBy(delta: (Int, Int), avatar: ActorAvatar): OutcomeEnum.Value = {
    move(_.moveBy(delta), avatar)
  }

  private[this] def moveTo(delta: (Int, Int), avatar: ActorAvatar): OutcomeEnum.Value = {
    move(_.moveTo(delta), avatar)
  }

  private[this] def move(mover: ActorAvatar => ActorAvatar, avatar: ActorAvatar): OutcomeEnum.Value = {

    // set the avatar next position
    var moved = mover(avatar)

    // get the new x and x positons to check for boundary break
    val px = moved.position._1
    val py = moved.position._2

    // optimistically think the move will be okay
    var ok = true

    // check the blocks to see that there is no object in the way
    blocks.foreach(f => if(
        (f.pos._1 == moved.pos._1) &&
        (f.pos._2 == moved.pos._2) &&
        f.kind == FixedKind
      ) ok = false)

    if( !(px >= 0 && px < size._1) || !(py >= 0 && py < size._2))
      ok = false

    if (ok) {
      // set the current piece to be the moved piece
     //println(avatar.ident + " has moved to " + px + "," + py)
     avatar.immutableMove(new XY(px, py))
    }

    ok match {
      case(true)  => OutcomeEnum.SUCCESS
      case(false) => OutcomeEnum.FAILURE
    }
  }

  /**
   * Render a piece at a set location. This will be a structural element
   * that will define boundaries such as walls, doors and windows
   *
   * @param point the x,y coordinates
   */
  def renderBlock(point: (Int, Int)) {

    val px = (math.floor(  point._1 / SWGUI.blockSize)).toInt
    val py = (size._2 - 1 - math.floor(point._2 / SWGUI.blockSize)).toInt

    blocks += new Block(new XY(px,py), FixedKind)

    var text = Source.fromFile(COORDINATE_FILE).mkString
    val writer = new PrintWriter(new File(COORDINATE_FILE))

    writer.append(text.+(point)+",")
    writer.close()

  }

  /**
   * Move the current avatar - wrong as there needs to be more thn one of them
   * @param act
   * @return
   */
  def move(act: RequestEnum.Value, avatar: ActorAvatar): OutcomeEnum.Value = {

    val orientation = avatar.orientation;
    val direction = act;

    // based on the orientation and direction we can work out the next xy coordinates
    // using the CoordTranslater
    val xy = CoordTranslater.getTransformation(avatar.position,orientation,direction)

    // publish the move event for the reactor to fire
    publish(AMovementEvent)

    // call to move the actor to a new block
    val outcome = moveTo(xy, avatar)

    outcome
  }
}