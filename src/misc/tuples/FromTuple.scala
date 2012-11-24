package ScalaWorld.misc.tuples

import scala.Predef._


class FromTuple(map: Map[Tuple2[String,String],Tuple2[String,String]], target:String) {

   val fromComponents = ((map map(e => e._1._1 + " " + e._1._1.toLowerCase)).toList :::
                         (map map(f => f._2._1 + " " + f._2._1.toLowerCase)).toList).toSet

    def emit: String = {
       "  FROM ".concat(fromComponents.reduceLeft(_ + ", " + _)).concat("\n")
    }

}
