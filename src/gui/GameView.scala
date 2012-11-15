package gui

import translations.XY
import scala.collection.mutable.Map

case class GameView(blocks:  Map[XY,Block], gridSize: (Int, Int), avatars: List[ActorAvatar])