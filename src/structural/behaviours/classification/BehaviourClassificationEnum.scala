package ScalaWorld.structural.behaviours.classification

object BehaviourClassificationEnum extends Enumeration {

  val GOD          = Value("God behavior")
  val AUTONOMOMOUS = Value("Autonomous behavior")

  val MOVE    = Value("Move behaviour")
  val COLLIDE = Value("Collide behaviour")
  val EXPLORE = Value("Explore behaviour")
  val AVOID   = Value("Avoid behaviour")
  val FOLLOW  = Value("Follow behaviour")
  val MAP     = Value("Map behaviour")
  val SLEEP   = Value("Sleep behaviour")
}
