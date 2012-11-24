package ScalaWorld.messaging

import gui.ActorAvatar
import ScalaWorld.structural.{Robot, Animated}
import ScalaWorld.enumerations._
import java.util.concurrent.Callable
import actors.threadpool.Future

abstract class SWRequest(act: RequestEnum.Value, robot: Robot) extends Callable[OutcomeEnum.Value] {

  val myaction:RequestEnum.Value = act
}

object SWRequest

