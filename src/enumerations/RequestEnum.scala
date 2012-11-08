package ScalaWorld.enumerations

/**
 * Created with IntelliJ IDEA.
 * User: martin
 * Date: 07/11/12
 * Time: 14:04
 * To change this template use File | Settings | File Templates.
 */
object RequestEnum extends Enumeration {

  val FORWARD      = Value(0,"Forward action")
  val RIGHT        = Value(1,"Right action")
  val BACK         = Value(2,"Back action")
  val LEFT         = Value(3,"Left action")

  val ROTATE_LEFT  = Value("Rotate left action")
  val ROTATE_RIGHT = Value("Rotate right action")

  val START        = Value("Start action")
  val STOP         = Value("Stop action")

  val GOAL         = Value("Goal action")

  val DEFAULT      = Value("Default action")
}
