package gui

/**
 * Animated avatar
 * @param pos the avatars position
 * @param kind what kind is the avatar
 * @param locals a pool of all the
 */
case class ActorAvatar(pos: (Double, Double), kind: PieceKind, locals: Seq[(Double, Double)]) {

  def current: Seq[Block] = locals map {
    case (x, y) => Block((math.floor(x + pos._1).toInt,math.floor(y + pos._2).toInt),kind)
  }

  def moveBy(delta: (Double, Double)): ActorAvatar =
    copy(pos = (pos._1 + delta._1, pos._2 + delta._2))
}

case object ActorAvatar {

  def apply(pos: (Double, Double), kind: PieceKind): ActorAvatar =
    kind match {
      case AnimKind  => ActorAvatar (pos, kind, Seq((0.0, 0.0)))
      case FixedKind => ActorAvatar( pos, kind, Seq((0.0, 0.0)))
    }
}