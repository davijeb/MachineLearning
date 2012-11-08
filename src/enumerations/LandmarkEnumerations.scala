package ScalaWorld.enumerations


object LandmarkEnumerations extends Enumeration {

  val WALL_F       = Value("Wall to the front")
  val WALL_R       = Value("Wall to the right")
  val WALL_B       = Value("Wall to the rear")
  val WALL_L       = Value("Wall to the left")
  val CORNER_FL    = Value("Corner to the front left")
  val CORNER_FR    = Value("Corner to the front right")
  val CORNER_BL    = Value("Corner to the back left")
  val CORNER_BR    = Value("Corner to the back right")
  val CORRIDOR_LR  = Value("Corridor to the left and right")
  val CORRIDOR_FB  = Value("Corner to the front and back")
  val BLINDALLEY_L = Value("Blind alley to the left")
  val BLINDALLEY_F = Value("Blind alley to the right")
  val BLINDALLEY_R = Value("Blind alley to the rear")
  val BLINDALLEY_B = Value("Blind alley to the front")
  val TRAP         = Value("Closed area")
  val EMPTY_SPACE  = Value("No landmarks nearby")

}

object Orientation extends Enumeration{

  val North = Value(0,"North")
  val East  = Value(1,"East")
  val South = Value(2,"South")
  val West  = Value(3,"West")

}