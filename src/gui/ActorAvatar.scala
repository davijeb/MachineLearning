package gui

import ScalaWorld.enumerations._

/**
 * Animated avatar
 * @param pos avatar position
 * @param kind what kind it is
 * @param locals the local blocks
 * @param orientation orientation
 */
case class ActorAvatar(pos: (Double, Double), kind: PieceKind, locals: Seq[(Double, Double)], orientation: Orientation.Value) {

  def current: Seq[Block] = locals map {
    case (x, y) => Block((math.floor(x + pos._1).toInt,math.floor(y + pos._2).toInt),kind)
  }

  def moveBy(delta: (Double, Double)): ActorAvatar =
    copy(pos = (pos._1 + delta._1, pos._2 + delta._2))
}

case object ActorAvatar {

  def apply(pos: (Double, Double), kind: PieceKind, orientation: Orientation.Value): ActorAvatar =
    kind match {
      case AnimKind  => ActorAvatar (pos, kind, Seq((0.0, 0.0)), Orientation.North)
      case FixedKind => ActorAvatar( pos, kind, Seq((0.0, 0.0)), Orientation.North)
    }
}