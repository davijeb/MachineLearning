package ScalaWorld.actions

/**
 * Created with IntelliJ IDEA.
 * User: Jez
 * Date: 09/11/12
 * Time: 14:23
 * To change this template use File | Settings | File Templates.
 */

import actors.ReplyReactor
import scala.actors._

class Wakeup[A](millis: Int, who: ReplyReactor, alarm: A) extends Thread {
  val done = new java.util.concurrent.atomic.AtomicBoolean(false)
  override def run {
    while (true) {
      who ! alarm
      Thread.sleep(millis)
    }
  }
}
case object BEEP {}
