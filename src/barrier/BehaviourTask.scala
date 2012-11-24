package ScalaWorld.barrier

import java.util.concurrent.CyclicBarrier
import ScalaWorld.structural.behaviours.{Behaviours, Behaviour}

class BehaviourTask(cb: CyclicBarrier, b: Behaviours) extends Runnable {

  override def run() {

    println("Do inhibitions")
    cb.await()
    println("Do sensors")
    println("Do algo")
  }
}
