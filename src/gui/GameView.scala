package gui

import translations.XY
import scala.collection.mutable.Map
import ScalaWorld.structural.Robot

case class GameView(blocks:  Map[XY,Block], gridSize: (Int, Int), avatars: List[Robot])