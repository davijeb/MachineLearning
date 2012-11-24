package ScalaWorld.misc.tuples

import scala.Predef._


class RestrictionTuple(table: String, column:String, values: List[Any]) {

  def emit: String = "   AND " + table.toLowerCase + "." + column + " IN ("+ values.reduceLeft(_ + ", " + _) + ")"

}
