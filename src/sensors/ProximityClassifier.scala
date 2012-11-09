package ScalaWorld.sensors

import ScalaWorld.Landmark
import ScalaWorld.enumerations.LandmarkEnumerations._

/**
 * Created with IntelliJ IDEA.
 * User: martin
 * Date: 06/11/12
 * Time: 16:07
 * To change this template use File | Settings | File Templates.
 */
class ProximityClassifier(senses: Tuple4[Boolean, Boolean, Boolean, Boolean]) extends Classifier {

  def doit(): Landmark = {

    senses match {
      case(true, true, true, true)     => new Landmark(TRAP)              // 1111
      case(false, false, false, false) => new Landmark(EMPTY_SPACE)       // 0000
      case(true, false, false, false)  => new Landmark(WALL_L)            // 1000
      case(false, true, false, false)  => new Landmark(WALL_F)            // 0100
      case(false, false, true, false)  => new Landmark(WALL_R)            // 0010
      case(false, false, false, true)  => new Landmark(WALL_B)            // 0001
      case(true, true, false, false)   => new Landmark(CORNER_FL)         // 1100
      case(false, true, true, false)   => new Landmark(CORNER_FR)         // 0110
      case(true, false, false, true)   => new Landmark(CORNER_BL)         // 1001
      case(false, false, true, true)   => new Landmark(CORNER_BR)         // 0011
      case(true, false, true, false)   => new Landmark(CORRIDOR_LR)       // 1010
      case(false, true, false, true)   => new Landmark(CORRIDOR_FB)       // 0101
      case(true, true, false, true)    => new Landmark(BLINDALLEY_L)      // 1101
      case(true, true, true, false)    => new Landmark(BLINDALLEY_F)      // 1110
      case(false, true, true, true)    => new Landmark(BLINDALLEY_R)      // 0111
      case(true, false, true, true)    => new Landmark(BLINDALLEY_B)      // 1011
    }
  }

}
