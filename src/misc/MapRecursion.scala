package ScalaWorld.misc

import org.squeryl._
import scala.Tuple2
import tuples.{RestrictionTuple, JoinTuple, FromTuple, SelectTuple}

object MapRecursion extends App {

  val st      = new SelectTuple("C","Name")
  val rt      = new RestrictionTuple("CAA","CounterpartyAccountAliasValue", List(1,2,3,4,5,6,7,8,9,10))

  var tree   = Map[Tuple2[String,String],TableNode]()
  var map    = Map[Tuple2[String,String],Tuple2[String,String]]()
  var newMap = Map[Tuple2[String,String],Tuple2[String,String]]()

  map += Tuple2("C","EntityId")                      -> Tuple2("CA","CounterpartyId")
  map += Tuple2("CA","EntityId")                     -> Tuple2("CAAS","EntityId")
  map += Tuple2("CAAS","CounterpartyAccountAliasId") -> Tuple2("CAA","CounterpartyAccountAliasId")
  map += Tuple2("CAAS","CounterpartyAccountAliasId") -> Tuple2("RA","CounterpartyAccountAliasId")

  val ft = new FromTuple(map,"")
  val jt = new JoinTuple(map,"")

  val sql = st.emit + ft.emit +  jt.emit + rt.emit

  def splitMap(table: Tuple2[String,String]): Map[Tuple2[String,String],Tuple2[String,String]] = {
    map.foreach(
      x => x match {
        case (a, b) => add(a,b)
      }
    )
    newMap
  }

  makeRI

  println(tree)

  walk("C")

  def makeRI(): Map[Tuple2[String,String],Tuple2[String,String]] = {
    map.foreach(
      x => x match {
        case (a, b) => add(a,b)
      }
    )
    newMap
  }

  def add(a: Tuple2[String,String], b: Tuple2[String,String]) = {

    val start:TableNode = tree.getOrElse(a, new TableNode(a))
    val end:TableNode   = tree.getOrElse(b, new TableNode(b))

    start.fks += b
    end.pks   += a
  }

  def walk(table: String) = {
    tree foreach(
      x => x match {
        case (a, b) => if(table.eq(a._1)) {println(table)} }
      )
  }

  def recurse(table: String, list: List[Tuple2[String,String]]) = {

  }

}
