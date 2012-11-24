package ScalaWorld.barrier

import java.util.concurrent.CyclicBarrier
import ScalaWorld.structural.behaviours.specific.BehaviourMove

object BarrierTest extends App {


      val cb = new CyclicBarrier(3, new Runnable {
        def run() {println("All parties have arrived at the barrier - so lets continue")}
      })

//      val t1:Thread = new Thread(new BehaviourTask(cb, new BehaviourMove("",null)))
//      val t2:Thread = new Thread(new BehaviourTask(cb, new BehaviourMove("",null)))
//      val t3:Thread = new Thread(new BehaviourTask(cb, new BehaviourMove("",null)))
//
//      t1.start
//      t2.start
//      t3.start


}
