package ScalaWorld.misc

class PersonsNameSlightlyMonadic {

  case class Thing[+A](value: A) {
    def bind[B](f: A => Thing[B]) = f(value)
  }

  val firstName:Thing[String] = Thing("Jeremy")
  val surname:Thing[String]   = Thing("Davies")

  def fullName(): Thing[String] = {
    val fname = firstName
    if (fname != null) {
      val lname = surname
      if (lname != null) {
        Thing(fname + " " + lname)
      } else {
        Thing(null)
      }
    } else {
      Thing(null)
    }
  }

}
