package gui

import ScalaWorld.enumerations._
import ScalaWorld.structural.SWActor
import translations.XY
import scala.collection.mutable.Seq

/**
 * Animated avatar
 * @param pos avatar position
 * @param kind what kind it is
 * @param locals the local blocks
 * @param orientation orientation
 */
case class ActorAvatar(pos: XY, kind: PieceKind, locals: Seq[(Int, Int)], orientation: Orientation.Value, actor: SWActor) {

  def current: Seq[Block] = locals map {
    case (x, y) => Block((math.floor(x + pos._1).toInt,math.floor(y + pos._2).toInt),kind)
  }

  def moveBy(delta: (Int, Int)): ActorAvatar =
    copy(pos =  new XY (pos._1 + delta._1, pos._2 + delta._2))

  def getMoveCoordinateType(delta: (Int, Int)): PieceKind =
    moveBy(delta).kind

  def moveTo(newPos: (Int, Int)): ActorAvatar =
    copy(pos = new XY(newPos._1, newPos._2))
}

case object ActorAvatar {

  def apply(pos: XY, kind: PieceKind, orientation: Orientation.Value, actor: SWActor): ActorAvatar =
    kind match {
      case AnimKind  => ActorAvatar (pos, kind, Seq((0, 0)), Orientation.North, new SWActor())
      case FixedKind => ActorAvatar( pos, kind, Seq((0, 0)), Orientation.North, new SWActor())
    }
}