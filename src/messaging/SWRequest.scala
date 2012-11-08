package ScalaWorld.messaging

import ScalaWorld.structural.Animated
import ScalaWorld.enumerations._
import java.util.concurrent.Callable
import actors.threadpool.Future

abstract class SWRequest(act: RequestEnum.Value, obj: Animated) extends Callable[OutcomeEnum.Value] {

  val myaction:RequestEnum.Value = act
}

object SWRequest

