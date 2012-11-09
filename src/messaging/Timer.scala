package ScalaWorld.messaging

import java.util.TimerTask
import com.sun.xml.internal.ws.util.StringUtils
import javax.swing.SwingUtilities

/**
 * Implementation of the java.util.Timer but utilising Scala currying
 */
object Timer {
  def apply(interval: Int)(op: => Any) {
    val timeOut = new javax.swing.AbstractAction() {
      def actionPerformed(e : java.awt.event.ActionEvent) = op
    }
    new javax.swing.Timer(interval, timeOut).start()
  }
}