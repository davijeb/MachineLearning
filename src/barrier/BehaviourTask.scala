package ScalaWorld.barrier

import java.util.concurrent.CyclicBarrier
import ScalaWorld.structural.behaviours.Behaviour

class BehaviourTask(cb: CyclicBarrier, b: Behaviour) extends Runnable {

  override def run() {

    println("Do inhibitions")
    cb.await();
    println("Do sensors")
    println("Do algo")
  }
}
