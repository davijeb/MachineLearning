package translations

class XY(xPos: Int, yPos: Int) extends Tuple2[Int,Int](xPos,yPos) {

  val x: Int = xPos
  val y: Int = yPos

  def + (that: XY): XY = new XY(xPos + that.x, y + that.y)

}