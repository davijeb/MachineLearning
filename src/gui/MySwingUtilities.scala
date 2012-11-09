package ScalaWorld.gui

/**
 * Created with IntelliJ IDEA.
 * User: Jez
 * Date: 09/11/12
 * Time: 14:15
 * To change this template use File | Settings | File Templates.
 */
object MySwingUtilities {
  def invokeLater[X](exp : => X) {
    import javax.swing.SwingUtilities

    SwingUtilities invokeLater (new Runnable() {
      def run = exp
    })
  }
}