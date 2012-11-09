package ScalaWorld.sensors.data

import gui.PieceKind
import translations.XY

/**
 * Created with IntelliJ IDEA.
 * User: Jez
 * Date: 09/11/12
 * Time: 16:26
 * To change this template use File | Settings | File Templates.
 */
class MovementSensorData(forward: PieceKind, right: PieceKind, backwards: PieceKind, left: PieceKind) {

  val f = forward
  val r = right
  val b = backwards
  val l = left
}
