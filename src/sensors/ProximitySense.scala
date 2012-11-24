package ScalaWorld.sensors

import actors.Actor

/**
 * Determines proximity to an object
 */
class ProximitySense(sensors: List[Sensor]) extends Sense {

  /**
   * Evaluate all the sensors and return a symbolic
   */
  def evaluate(): Boolean = {
    true
  }

}
