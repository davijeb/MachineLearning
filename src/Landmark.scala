package ScalaWorld

import enumerations.LandmarkEnumerations
import structural.Wall

/**
 * Created with IntelliJ IDEA.
 * User: martin
 * Date: 06/11/12
 * Time: 14:25
 * To change this template use File | Settings | File Templates.
 */


// test comment


class Landmark(name: LandmarkEnumerations.Value) {

  override def toString = "Landmark is: " + name
}
