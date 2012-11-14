package ScalaWorld.messaging

import gui.ActorAvatar
import ScalaWorld.structural.Animated
import ScalaWorld.enumerations._
import java.util.concurrent.Callable
import actors.threadpool.Future

abstract class SWRequest(act: RequestEnum.Value, avatar: ActorAvatar) extends Callable[OutcomeEnum.Value] {

  val myaction:RequestEnum.Value = act
}

object SWRequest

