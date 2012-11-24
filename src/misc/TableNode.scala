package ScalaWorld.misc

import collection.mutable.ListBuffer
import scala.Tuple2

class TableNode(tc: Tuple2[String,String]) {

  val table  = tc._1
  val column = tc._2

  val pks = new ListBuffer[Tuple2[String,String]]
  val fks = new ListBuffer[Tuple2[String,String]]

  MapRecursion.tree += tc -> this

  override def toString: String = table + " p[" + pks + "] f[" + fks + "] *** "

}
