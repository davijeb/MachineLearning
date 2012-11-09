package translations

import ScalaWorld.enumerations.{RequestEnum, Orientation}
import translations._

object CoordTranslater {

  def getTransformation(
                         start:XY,
                         orientation: Orientation.Value,
                         direction: RequestEnum.Value): XY = {

    val matrix = List(
      List(new XY(0,1),  new XY(1,0),  new XY(0,-1), new XY(-1,0)),
      List(new XY(1,0),  new XY(0,-1), new XY(-1,0), new XY(0,1)),
      List(new XY(0,-1), new XY(-1,0), new XY(0,1),  new XY(1,0)),
      List(new XY(-1,0), new XY(0,1),  new XY(1,0),  new XY(0,-1))
    )

    val transformation = matrix(orientation.id)(direction.id)

    start+transformation
  }
}


