package gui

import io.Source
import sys.process._
import java.io.{PrintWriter, File}
import dbc.statement.JoinType.Outer.Full
import ScalaWorld.enumerations.{RequestEnum, OutcomeEnum, Orientation}
import ScalaWorld.structural.{Animated, SWActor}
import translations.{XY, CoordTranslater}
import swing.Publisher
import ScalaWorld.gui.{ASensorEvent, AMovementEvent}
import collection.script._
import scala.collection.mutable.Seq
import collection.mutable
import ScalaWorld.sensors.Sensor
import ScalaWorld.sensors.data.MovementSensorData

class Stage(size: (Int, Int)) extends Publisher {

  // start - publish/subscribe for movement events
    val set = new mutable.HashSet[MovementSensorData] with mutable.ObservableSet[MovementSensorData] { }
    type Msg = Message[MovementSensorData] with mutable.Undoable

    type Sub = mutable.Subscriber[Msg, mutable.ObservableSet[MovementSensorData]]

//    val sub = new Sub() {
//      def notify(pub: mutable.ObservableSet[String], event: Msg): Unit = {
//        println("%s sent %s".format(pub, event))
//        event match {
//          case r:Remove[_] => println("undo!"); event.undo()
//          case _ =>
//        }
//      }
//    }

    val sub2 = new Sensor()

    //set.subscribe(sub)
    set.subscribe(sub2)
  // end

  private[this] val COORD_FILE = "c:/Users/Public/coordinates.txt"

  // Start at 0,0 (bottom left)
  private[this] def dropOffPos = new XY(0,0)

  // Create an avatar to be inserted into the board at that position
  var currentAvatar = ActorAvatar(dropOffPos, AnimKind, Orientation.North, new SWActor())

  // Initialise the blocks with a single entry at the origin and to this we
  // add the initial avatar - the +: means prepend to the sequence
  private[this] var blocks = Block((0, 0), AnimKind) +: currentAvatar.current

  // Define a game view to hold onto the blocks, stage size and the
  def view: GameView = GameView(blocks, size, currentAvatar.current)

  if (Source.fromFile(COORD_FILE).mkString != null)
    loadFromFile(Option(Source.fromFile(COORD_FILE).mkString))

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
  def moveUp()    = moveBy(0, 1)
  def moveRight() = moveBy(1, 0)
  def moveDown()  = moveBy(0, -1)
  def moveLeft()  = moveBy(-1, 0)

  private[this] def moveBy(delta: (Int, Int)): this.type = {

    // set the avatar next position
    val moved = currentAvatar.moveBy(delta)

    // get the new x and x positons to check for boundary break
    val px = moved.pos._1
    val py = moved.pos._2

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
      // unload the piece from the blocks and add the moved piece to the blocks
      val unloaded = unload(currentAvatar, blocks)
      blocks = load(moved, unloaded)

      // set the current piece to be the moved piece
      currentAvatar = moved
    }

    this
  }

  private[this] def moveTo(newPos: (Int, Int)): OutcomeEnum.Value = {

    // set the avatar next position
    val moved = currentAvatar.moveTo(newPos)

    // get the new x and x positons to check for boundary break
    val px = moved.pos._1
    val py = moved.pos._2

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
      // unload the piece from the blocks and add the moved piece to the blocks
      val unloaded = unload(currentAvatar, blocks)
      blocks = load(moved, unloaded)

      // set the current piece to be the moved piece
      currentAvatar = moved
    }

    ok match {
      case(true)  => OutcomeEnum.SUCCESS
      case(false) => OutcomeEnum.FAILURE
    }
  }

  /**
   * Take the piece out of the block matrix
   * @param p the piece
   * @param bs the blocks
   * @return the blocks
   */
  private[this] def unload(p: ActorAvatar, bs: Seq[Block]): Seq[Block] = {
    // builds a new collection by applying a function to all elements in the list
    val currentPoss = p.current map {_.pos}
    // selects all the elements from a list which do not satisfy the predicate
    bs filterNot { currentPoss contains _.pos  }

  }

  /**
   * Load the piece into the block martix
   * @param p the piece
   * @param bs the blocks
   * @return the blocks
   */
  private[this] def load(p: ActorAvatar, bs: Seq[Block]): Seq[Block] =
    bs ++ p.current

  /**
   * Render a piece at a set location. This will be a structural element
   * that will define boundaries such as walls, doors and windows
   *
   * @param point the x,y coordinates
   */
  def renderBlock(point: (Int, Int)) {

    val px = (math.floor(  point._1 / SWGUI.blockSize)).toInt
    val py = (size._2 - 1 - math.floor(point._2 / SWGUI.blockSize)).toInt

    val piece = ActorAvatar(new XY(px,py), FixedKind, Orientation.North, new SWActor())
    val unloaded = unload(piece, blocks)
    val moved = piece.moveBy(px,py)
    blocks = load(piece, unloaded)

    var text = Source.fromFile(COORD_FILE).mkString
    val writer = new PrintWriter(new File(COORD_FILE))

    writer.append(text.+(point)+",")
    writer.close()

  }

  def move(act: RequestEnum.Value, obj: Animated): OutcomeEnum.Value = {

    val orientation = currentAvatar.orientation;
    val direction = act;

    // based on the orientation and direction we can work out the next xy coordinates
    // using the CoordTranslater
    val xy = CoordTranslater.getTransformation(currentAvatar.pos,orientation,direction)

    // publish the move event for the reactor to fire
    publish(AMovementEvent)

    def moveUp()    = moveBy(0, 1)
    def moveRight() = moveBy(1, 0)
    def moveDown()  = moveBy(0, -1)
    def moveLeft()  = moveBy(-1, 0)

    // call to move the actor to a new block
    val outcome = moveTo(xy)

    set += new MovementSensorData(
      currentAvatar.getMoveCoordinateType(0,1),
      currentAvatar.getMoveCoordinateType(1,0),
      currentAvatar.getMoveCoordinateType(0,-1),
      currentAvatar.getMoveCoordinateType(-1,0)
    )

    outcome

  }
}