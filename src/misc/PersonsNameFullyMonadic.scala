package ScalaWorld.misc

object PersonsNameFullyMonadic extends App {

  case class Thing[+A](value: A) {
    def bind[B](f: A => Thing[B]) = f(value)
  }

  val firstName:Option[String] = Option("Jeremy")
  val surname:Option[String]   = Option("Davies")


}
