package ScalaWorld.misc.tuples

import scala.Predef._


class JoinTuple(map: Map[Tuple2[String,String],Tuple2[String,String]], target:String) {

  def emit: String = {
      var count = 0
      var r = ""
      map.foreach(
          x => x match {
          case (a, b) =>
            r +=(getRestriction(count) +  // WHERE or AND
              " "                      +  // space
              a._1.toLowerCase         +  // Table 1
              "."                      +  // dot
              a._2                     +  // Column 1
              " = "                    +  // Equals
              b._1.toLowerCase         +  // Table 2
              "."                      +  // dot
              b._2                     +  // Column 2
              "\n");
              count +=1
          }
      )
      r
  }

  def getRestriction(count: Int): String = { if (count == 0) " WHERE" else "   AND"}

}
