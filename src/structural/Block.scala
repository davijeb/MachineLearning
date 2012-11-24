package ScalaWorld.structural

import behaviours.Behaviour
import ScalaWorld.enumerations.{Orientation, LandmarkEnumerations}

/**
 * Created with IntelliJ IDEA.
 * User: martin
 * Date: 07/11/12
 * Time: 16:31
 * To change this template use File | Settings | File Templates.
 */
class Block (posX: Int, posY: Int, blockOpacity: Int = 0, blockSolidity: Int = 100, blockOccupier: Behaviour, occupierOrientation: Orientation.Value , myGrid: Grid ) {

  val x = posX
  val y = posY

  val occupier = blockOccupier
  val orientaion = occupierOrientation
  val opacity = blockOpacity
  val solidity = blockSolidity

}
