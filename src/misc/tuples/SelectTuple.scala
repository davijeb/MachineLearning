package ScalaWorld.misc.tuples

import scala.Predef._
import scala.Tuple2


class SelectTuple(table: String, column:String) {

  def emit = "SELECT " + table + "." + column + "\n"

}
