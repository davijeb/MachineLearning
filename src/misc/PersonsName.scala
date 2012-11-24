package ScalaWorld.misc

/**
 * Created with IntelliJ IDEA.
 * User: Jez
 * Date: 16/11/12
 * Time: 15:17
 * To change this template use File | Settings | File Templates.
 */
class PersonsNameNoMonad {

  val firstName = "Jeremy"
  val surname   = "Davies"

  def fullName(): String = {
    val fname = firstName
    if (fname != null) {
      val lname = surname
      if (lname != null) {
        fname + " " + lname
      } else {
        null
      }
    } else {
      null
    }
  }

}
