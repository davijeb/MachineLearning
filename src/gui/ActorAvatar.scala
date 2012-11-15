package gui

import ScalaWorld.enumerations._
import translations.XY
import scala.collection.mutable.Seq
import ScalaWorld.structural.behaviours.{BehaviourClassificationEnum, MoveBehaviour, Behaviour}
import ScalaWorld.structural.Robot

/**
 * Animated avatar
 * @param pos avatar position
 * @param kind what kind it is
 * @param orientation orientation
 */
case class ActorAvatar(name:String, pos: XY, kind: PieceKind, orientation: Orientation.Value) {

  val ident = name
  var position = pos

  def moveBy(delta: (Int, Int)): ActorAvatar =
    copy(pos =  new XY (pos._1 + delta._1, pos._2 + delta._2))

  def getMoveCoordinateType(delta: (Int, Int)): PieceKind =
    moveBy(delta).kind

  def moveTo(newPos: (Int, Int)): ActorAvatar =
    copy(pos = new XY(newPos._1, newPos._2))

  def immutableMove(delta: XY) {position = delta}

}